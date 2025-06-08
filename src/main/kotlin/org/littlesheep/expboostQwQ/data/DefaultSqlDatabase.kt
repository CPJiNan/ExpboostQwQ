package org.littlesheep.expboostQwQ.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import taboolib.common5.Coerce
import taboolib.library.configuration.ConfigurationSection
import taboolib.module.database.ColumnOptionSQL
import taboolib.module.database.ColumnTypeSQL
import taboolib.module.database.HostSQL
import taboolib.module.database.Table

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ.data
 *
 * @author 季楠
 * @since 2025/6/8 11:16
 */
class DefaultSqlDatabase(section: ConfigurationSection, name: String) : Database {
    private val host = HostSQL(section)
    private val table = Table(name, host) {
        add("key") {
            type(ColumnTypeSQL.TEXT) {
                options(ColumnOptionSQL.PRIMARY_KEY)
            }
        }
        add("value") {
            type(ColumnTypeSQL.TEXT)
        }
    }
    private val dataSource by lazy { host.createDataSource() }

    init {
        table.createTable(dataSource, checkExists = true)
    }

    override val type: DatabaseType = DatabaseType.SQL

    override fun getKeys(): Set<String> {
        return getValues().keys
    }

    override fun getValues(): Map<String, Any?> {
        return table.select(dataSource) {
            rows("key", "value")
        }.map {
            getString("key") to getString("value")
        }.toMap()
    }

    override fun toMap(): Map<String, Any?> {
        return getValues()
    }

    override operator fun contains(path: String): Boolean {
        return table.select(dataSource) {
            rows("key")
            where("key" eq path)
            limit(1)
        }.find()
    }

    override fun isSet(path: String): Boolean {
        return contains(path)
    }

    override operator fun get(path: String): Any? {
        return get(path, null)
    }

    override operator fun get(path: String, def: Any?): Any? {
        return table.select(dataSource) {
            rows("key", "value")
            where("key" eq path)
            limit(1)
        }.firstOrNull {
            getString("value")
        } ?: def
    }

    override operator fun set(path: String, value: Any?) {
        if (value == null) {
            table.delete(dataSource) {
                where { "key" eq path }
            }
            return
        }
        if (contains(path)) table.update(dataSource) {
            set("value", value)
            where("key" eq path)
        } else {
            table.insert(dataSource, "key", "value") {
                value(path, value)
            }
        }
    }

    override fun save() {}

    override fun reload() {}

    override fun clear() {
        table.delete(dataSource) { }
    }

    override fun getString(path: String): String? {
        return getString(path, null)
    }

    override fun getString(path: String, def: String?): String? {
        return get(path)?.toString() ?: def
    }

    override fun isString(path: String): Boolean {
        return get(path) != null
    }

    override fun getInt(path: String): Int {
        return getInt(path, 0)
    }

    override fun getInt(path: String, def: Int): Int {
        return get(path).toString().toIntOrNull() ?: def
    }

    override fun isInt(path: String): Boolean {
        return get(path).toString().toIntOrNull() != null
    }

    override fun getBoolean(path: String): Boolean {
        return getBoolean(path, false)
    }

    override fun getBoolean(path: String, def: Boolean): Boolean {
        return get(path).toString().toBooleanStrictOrNull() ?: def
    }

    override fun isBoolean(path: String): Boolean {
        return get(path).toString().toBooleanStrictOrNull() != null
    }

    override fun getDouble(path: String): Double {
        return getDouble(path, 0.0)
    }

    override fun getDouble(path: String, def: Double): Double {
        return get(path).toString().toDoubleOrNull() ?: def
    }

    override fun isDouble(path: String): Boolean {
        return get(path).toString().toDoubleOrNull() != null
    }

    override fun getLong(path: String): Long {
        return getLong(path, 0)
    }

    override fun getLong(path: String, def: Long): Long {
        return get(path).toString().toDoubleOrNull()?.toLong() ?: def
    }

    override fun isLong(path: String): Boolean {
        return get(path).toString().toDoubleOrNull()?.toLong() != null
    }

    override fun getList(path: String): List<*>? {
        return getList(path, null)
    }

    override fun getList(path: String, def: List<*>?): List<*>? {
        val value = get(path)?.toString() ?: return def
        val type = object : TypeToken<List<*>>() {}.type
        return Gson().fromJson(value, type)
    }

    override fun isList(path: String): Boolean {
        return getList(path) != null
    }

    override fun getStringList(path: String): List<String> {
        return getList(path)?.map { Coerce.toString(it) }?.toList() ?: emptyList()
    }

    override fun getIntegerList(path: String): List<Int> {
        return getList(path)?.map { Coerce.toInteger(it) }?.toList() ?: emptyList()
    }

    override fun getBooleanList(path: String): List<Boolean> {
        return getList(path)?.map { Coerce.toBoolean(it) }?.toList() ?: emptyList()
    }

    override fun getDoubleList(path: String): List<Double> {
        return getList(path)?.map { Coerce.toDouble(it) }?.toList() ?: emptyList()
    }

    override fun getFloatList(path: String): List<Float> {
        return getList(path)?.map { Coerce.toFloat(it) }?.toList() ?: emptyList()
    }

    override fun getLongList(path: String): List<Long> {
        return getList(path)?.map { Coerce.toLong(it) }?.toList() ?: emptyList()
    }

    override fun getByteList(path: String): List<Byte> {
        return getList(path)?.map { Coerce.toByte(it) }?.toList() ?: emptyList()
    }

    override fun getCharacterList(path: String): List<Char> {
        return getList(path)?.map { Coerce.toChar(it) }?.toList() ?: emptyList()
    }

    override fun getShortList(path: String): List<Short> {
        return getList(path)?.map { Coerce.toShort(it) }?.toList() ?: emptyList()
    }

    override fun getMapList(path: String): List<Map<*, *>> {
        return getList(path)?.filterIsInstance<Map<*, *>>()?.toList() ?: emptyList()
    }
}