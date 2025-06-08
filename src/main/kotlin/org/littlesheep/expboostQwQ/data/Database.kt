package org.littlesheep.expboostQwQ.data

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ.data
 *
 * @author TabooLib, 季楠
 * @since 2025/6/7 18:51
 */
interface Database {
    /** 数据库类型 **/
    val type: DatabaseType

    /**
     * 获取此数据库中所有键的集合。
     *
     * @return 包含在此 [Database] 中的键集合。
     */
    fun getKeys(): Set<String>

    /**
     * 获取当前 [Database] 的所有值。
     *
     * @return 包含当前 [Database] 所有键值对的 Map。
     */
    fun getValues(): Map<String, Any?>

    /**
     * 将当前 [Database] 转换为 Map。
     *
     * @return 包含当前 [Database] 所有键值对的 Map。
     */
    fun toMap(): Map<String, Any?>

    /**
     * 检查此 [Database] 是否包含给定路径。
     *
     * 如果请求路径的值不存在但已指定默认值，则此方法将返回 true。
     *
     * @param path 要检查存在性的路径。
     * @return 如果此数据库包含请求的路径（通过默认值或已设置），则返回 true。
     */
    operator fun contains(path: String): Boolean

    /**
     * 方法 [contains] 的别名。
     */
    fun isSet(path: String): Boolean

    /**
     * 通过路径获取请求的对象。
     *
     * 如果对象不存在，则返回 null。
     *
     * @param path 要获取的对象的路径。
     * @return 请求的对象。
     */
    operator fun get(path: String): Any?

    /**
     * 通过路径获取请求的对象。
     *
     * 如果对象不存在但已指定默认值，则将返回默认值。
     * 如果对象不存在且未指定默认值，则返回 null。
     *
     * @param path 要获取的对象的路径。
     * @return 请求的对象。
     */
    operator fun get(path: String, def: Any?): Any?

    /**
     * 将指定路径设置为给定值。
     *
     * 如果值为 null，则会删除该条目。任何现有条目都将被替换，无论新值是什么。
     *
     * @param path 要设置的对象的路径。
     * @param value 要设置的新值。
     */
    operator fun set(path: String, value: Any?)

    /**
     * 保存当前 [Database] 中的所有数据。
     */
    fun save()

    /**
     * 重载当前 [Database] 中的所有数据。
     */
    fun reload()

    /**
     * 清除当前 [Database] 中的所有数据。
     */
    fun clear()

    /**
     * 通过路径获取请求的字符串。
     *
     * 如果字符串不存在但已指定默认值，则将返回默认值。
     * 如果字符串不存在且未指定默认值，则返回 null。
     *
     * @param path 要获取的字符串的路径。
     * @return 请求的字符串。
     */
    fun getString(path: String): String?

    /**
     * 通过路径获取请求的字符串，如果未找到则返回默认值。
     *
     * @param path 要获取的字符串的路径。
     * @param def 如果未找到路径或不是字符串时要返回的默认值。
     * @return 请求的字符串。
     */
    fun getString(path: String, def: String?): String?

    /**
     * 检查指定路径是否为字符串。
     *
     * 如果路径存在但不是字符串，则返回 false。
     * 如果路径不存在，则返回 false。
     * 如果路径不存在但已指定默认值，则检查该默认值是否为字符串并相应返回。
     *
     * @param path 要检查的字符串的路径。
     * @return 指定路径是否为字符串。
     */
    fun isString(path: String): Boolean

    /**
     * 通过路径获取请求的整数。
     *
     * 如果整数不存在但已指定默认值，则将返回默认值。
     * 如果整数不存在且未指定默认值，则返回 0。
     *
     * @param path 要获取的整数的路径。
     * @return 请求的整数。
     */
    fun getInt(path: String): Int

    /**
     * 通过路径获取请求的整数，如果未找到则返回默认值。
     *
     * @param path 要获取的整数的路径。
     * @param def 如果未找到路径或不是整数时要返回的默认值。
     * @return 请求的整数。
     */
    fun getInt(path: String, def: Int): Int

    /**
     * 检查指定路径是否为整数。
     *
     * 如果路径存在但不是整数，则返回 false。
     * 如果路径不存在，则返回 false。
     * 如果路径不存在但已指定默认值，则检查该默认值是否为整数并相应返回。
     *
     * @param path 要检查的整数的路径。
     * @return 指定路径是否为整数。
     */
    fun isInt(path: String): Boolean

    /**
     * 通过路径获取请求的布尔值。
     *
     * 如果布尔值不存在但已指定默认值，则将返回默认值。
     * 如果布尔值不存在且未指定默认值，则返回 false。
     *
     * @param path 要获取的布尔值的路径。
     * @return 请求的布尔值。
     */
    fun getBoolean(path: String): Boolean

    /**
     * 通过路径获取请求的布尔值，如果未找到则返回默认值。
     *
     * @param path 要获取的布尔值的路径。
     * @param def 如果未找到路径或不是布尔值时要返回的默认值。
     * @return 请求的布尔值。
     */
    fun getBoolean(path: String, def: Boolean): Boolean

    /**
     * 检查指定路径是否为布尔值。
     *
     * 如果路径存在但不是布尔值，则返回 false。
     * 如果路径不存在，则返回 false。
     * 如果路径不存在但已指定默认值，则检查该默认值是否为布尔值并相应返回。
     *
     * @param path 要检查的布尔值的路径。
     * @return 指定路径是否为布尔值。
     */
    fun isBoolean(path: String): Boolean

    /**
     * 通过路径获取请求的双精度浮点数。
     *
     * 如果双精度浮点数不存在但已指定默认值，则将返回默认值。
     * 如果双精度浮点数不存在且未指定默认值，则返回 0。
     *
     * @param path 要获取的双精度浮点数的路径。
     * @return 请求的双精度浮点数。
     */
    fun getDouble(path: String): Double

    /**
     * 通过路径获取请求的双精度浮点数，如果未找到则返回默认值。
     *
     * @param path 要获取的双精度浮点数的路径。
     * @param def 如果未找到路径或不是双精度浮点数时要返回的默认值。
     * @return 请求的双精度浮点数。
     */
    fun getDouble(path: String, def: Double): Double

    /**
     * 检查指定路径是否为双精度浮点数。
     *
     * 如果路径存在但不是双精度浮点数，则返回 false。
     * 如果路径不存在，则返回 false。
     * 如果路径不存在但已指定默认值，则检查该默认值是否为双精度浮点数并相应返回。
     *
     * @param path 要检查的双精度浮点数的路径。
     * @return 指定路径是否为双精度浮点数。
     */
    fun isDouble(path: String): Boolean

    /**
     * 通过路径获取请求的长整数。
     *
     * 如果长整数不存在但已指定默认值，则将返回默认值。
     * 如果长整数不存在且未指定默认值，则返回 0。
     *
     * @param path 要获取的长整数的路径。
     * @return 请求的长整数。
     */
    fun getLong(path: String): Long

    /**
     * 通过路径获取请求的长整数，如果未找到则返回默认值。
     *
     * @param path 要获取的长整数的路径。
     * @param def 如果未找到路径或不是长整数时要返回的默认值。
     * @return 请求的长整数。
     */
    fun getLong(path: String, def: Long): Long

    /**
     * 检查指定路径是否为长整数。
     *
     * 如果路径存在但不是长整数，则返回 false。
     * 如果路径不存在，则返回 false。
     * 如果路径不存在但已指定默认值，则检查该默认值是否为长整数并相应返回。
     *
     * @param path 要检查的长整数的路径。
     * @return 指定路径是否为长整数。
     */
    fun isLong(path: String): Boolean

    /**
     * 通过路径获取请求的列表。
     *
     * 如果列表不存在但已指定默认值，则将返回默认值。
     * 如果列表不存在且未指定默认值，则返回 null。
     *
     * @param path 要获取的列表的路径。
     * @return 请求的列表。
     */
    fun getList(path: String): List<*>?

    /**
     * 通过路径获取请求的列表，如果未找到则返回默认值。
     *
     * @param path 要获取的列表的路径。
     * @param def 如果未找到路径或不是列表时要返回的默认值。
     * @return 请求的列表。
     */
    fun getList(path: String, def: List<*>?): List<*>?

    /**
     * 检查指定路径是否为列表。
     *
     * 如果路径存在但不是列表，则返回 false。
     * 如果路径不存在，则返回 false。
     * 如果路径不存在但已指定默认值，则检查该默认值是否为列表并相应返回。
     *
     * @param path 要检查的列表的路径。
     * @return 指定路径是否为列表。
     */
    fun isList(path: String): Boolean

    /**
     * 通过路径获取请求的字符串列表。
     *
     * 如果列表不存在但已指定默认值，则将返回默认值。
     * 如果列表不存在且未指定默认值，则返回空列表。
     *
     * 此方法将尝试将任何值转换为字符串（如果可能），但如果不兼容，可能会遗漏一些值。
     *
     * @param path 要获取的列表的路径。
     * @return 请求的字符串列表。
     */
    fun getStringList(path: String): List<String>

    /**
     * 通过路径获取请求的整数列表。
     *
     * 如果列表不存在但已指定默认值，则将返回默认值。
     * 如果列表不存在且未指定默认值，则返回空列表。
     *
     * 此方法将尝试将任何值转换为整数（如果可能），但如果不兼容，可能会遗漏一些值。
     *
     * @param path 要获取的列表的路径。
     * @return 请求的整数列表。
     */
    fun getIntegerList(path: String): List<Int>

    /**
     * 通过路径获取请求的布尔值列表。
     *
     * 如果列表不存在但已指定默认值，则将返回默认值。
     * 如果列表不存在且未指定默认值，则返回空列表。
     *
     * 此方法将尝试将任何值转换为布尔值（如果可能），但如果不兼容，可能会遗漏一些值。
     *
     * @param path 要获取的列表的路径。
     * @return 请求的布尔值列表。
     */
    fun getBooleanList(path: String): List<Boolean>

    /**
     * 通过路径获取请求的双精度浮点数列表。
     *
     * 如果列表不存在但已指定默认值，则将返回默认值。
     * 如果列表不存在且未指定默认值，则返回空列表。
     *
     * 此方法将尝试将任何值转换为双精度浮点数（如果可能），但如果不兼容，可能会遗漏一些值。
     *
     * @param path 要获取的列表的路径。
     * @return 请求的双精度浮点数列表。
     */
    fun getDoubleList(path: String): List<Double>

    /**
     * 通过路径获取请求的浮点数列表。
     *
     * 如果列表不存在但已指定默认值，则将返回默认值。
     * 如果列表不存在且未指定默认值，则返回空列表。
     *
     * 此方法将尝试将任何值转换为浮点数（如果可能），但如果不兼容，可能会遗漏一些值。
     *
     * @param path 要获取的列表的路径。
     * @return 请求的浮点数列表。
     */
    fun getFloatList(path: String): List<Float>

    /**
     * 通过路径获取请求的长整数列表。
     *
     * 如果列表不存在但已指定默认值，则将返回默认值。
     * 如果列表不存在且未指定默认值，则返回空列表。
     *
     * 此方法将尝试将任何值转换为长整数（如果可能），但如果不兼容，可能会遗漏一些值。
     *
     * @param path 要获取的列表的路径。
     * @return 请求的长整数列表。
     */
    fun getLongList(path: String): List<Long>

    /**
     * 通过路径获取请求的字节列表。
     *
     * 如果列表不存在但已指定默认值，则将返回默认值。
     * 如果列表不存在且未指定默认值，则返回空列表。
     *
     * 此方法将尝试将任何值转换为字节（如果可能），但如果不兼容，可能会遗漏一些值。
     *
     * @param path 要获取的列表的路径。
     * @return 请求的字节列表。
     */
    fun getByteList(path: String): List<Byte>

    /**
     * 通过路径获取请求的字符列表。
     *
     * 如果列表不存在但已指定默认值，则将返回默认值。
     * 如果列表不存在且未指定默认值，则返回空列表。
     *
     * 此方法将尝试将任何值转换为字符（如果可能），但如果不兼容，可能会遗漏一些值。
     *
     * @param path 要获取的列表的路径。
     * @return 请求的字符列表。
     */
    fun getCharacterList(path: String): List<Char>

    /**
     * 通过路径获取请求的短整数列表。
     *
     * 如果列表不存在但已指定默认值，则将返回默认值。
     * 如果列表不存在且未指定默认值，则返回空列表。
     *
     * 此方法将尝试将任何值转换为短整数（如果可能），但如果不兼容，可能会遗漏一些值。
     *
     * @param path 要获取的列表的路径。
     * @return 请求的短整数列表。
     */
    fun getShortList(path: String): List<Short>

    /**
     * 通过路径获取请求的映射列表。
     *
     * 如果列表不存在但已指定默认值，则将返回默认值。
     * 如果列表不存在且未指定默认值，则返回空列表。
     *
     * 此方法将尝试将任何值转换为映射（如果可能），但如果不兼容，可能会遗漏一些值。
     *
     * @param path 要获取的列表的路径。
     * @return 请求的映射列表。
     */
    fun getMapList(path: String): List<Map<*, *>>
}