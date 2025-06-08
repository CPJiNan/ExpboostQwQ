package org.littlesheep.expboostQwQ.data

import taboolib.module.configuration.Configuration
import taboolib.module.configuration.Type
import java.io.File

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ.data
 *
 * @author Taboolib, 季楠
 * @since 2025/6/7 19:15
 */
class DefaultJsonDatabase() : Database {
    private var file: File? = null
    private var config: Configuration = Configuration.empty()

    constructor(file: File) : this() {
        if (file.exists()) {
            this.file = file
            this.config = Configuration.loadFromFile(file, Type.JSON)
        }
    }

    constructor(contents: String) : this() {
        this.config = Configuration.loadFromString(contents, Type.JSON)
    }

    override val type: DatabaseType = DatabaseType.JSON

    override fun getKeys(): Set<String> {
        return config.getKeys(false)
    }

    override fun getValues(): Map<String, Any?> {
        return config.getValues(false)
    }

    override fun toMap(): Map<String, Any?> {
        return config.toMap()
    }

    override operator fun contains(path: String): Boolean {
        return config.contains(path)
    }

    override fun isSet(path: String): Boolean {
        return config.isSet(path)
    }

    override operator fun get(path: String): Any? {
        return config[path]
    }

    override operator fun get(path: String, def: Any?): Any? {
        return config[path, def]
    }

    override operator fun set(path: String, value: Any?) {
        config[path] = value
        save()
    }

    override fun save() {
        val localFile = file
        if (localFile != null) config.saveToFile(localFile)
    }

    override fun reload() {
        config.reload()
    }

    override fun clear() {
        config.clear()
        save()
    }

    override fun getString(path: String): String? {
        return config.getString(path)
    }

    override fun getString(path: String, def: String?): String? {
        return config.getString(path, def)
    }

    override fun isString(path: String): Boolean {
        return config.isString(path)
    }

    override fun getInt(path: String): Int {
        return config.getInt(path)
    }

    override fun getInt(path: String, def: Int): Int {
        return config.getInt(path, def)
    }

    override fun isInt(path: String): Boolean {
        return config.isInt(path)
    }

    override fun getBoolean(path: String): Boolean {
        return config.getBoolean(path)
    }

    override fun getBoolean(path: String, def: Boolean): Boolean {
        return config.getBoolean(path, def)
    }

    override fun isBoolean(path: String): Boolean {
        return config.isBoolean(path)
    }

    override fun getDouble(path: String): Double {
        return config.getDouble(path)
    }

    override fun getDouble(path: String, def: Double): Double {
        return config.getDouble(path, def)
    }

    override fun isDouble(path: String): Boolean {
        return config.isDouble(path)
    }

    override fun getLong(path: String): Long {
        return config.getLong(path)
    }

    override fun getLong(path: String, def: Long): Long {
        return config.getLong(path, def)
    }

    override fun isLong(path: String): Boolean {
        return config.isLong(path)
    }

    override fun getList(path: String): List<*>? {
        return config.getList(path)
    }

    override fun getList(path: String, def: List<*>?): List<*>? {
        return config.getList(path, def)
    }

    override fun isList(path: String): Boolean {
        return config.isList(path)
    }

    override fun getStringList(path: String): List<String> {
        return config.getStringList(path)
    }

    override fun getIntegerList(path: String): List<Int> {
        return config.getIntegerList(path)
    }

    override fun getBooleanList(path: String): List<Boolean> {
        return config.getBooleanList(path)
    }

    override fun getDoubleList(path: String): List<Double> {
        return config.getDoubleList(path)
    }

    override fun getFloatList(path: String): List<Float> {
        return config.getFloatList(path)
    }

    override fun getLongList(path: String): List<Long> {
        return config.getLongList(path)
    }

    override fun getByteList(path: String): List<Byte> {
        return config.getByteList(path)
    }

    override fun getCharacterList(path: String): List<Char> {
        return config.getCharacterList(path)
    }

    override fun getShortList(path: String): List<Short> {
        return config.getShortList(path)
    }

    override fun getMapList(path: String): List<Map<*, *>> {
        return config.getMapList(path)
    }
}