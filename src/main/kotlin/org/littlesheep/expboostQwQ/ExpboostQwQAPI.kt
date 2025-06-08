package org.littlesheep.expboostQwQ

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ
 *
 * @author LittleSheep, 季楠
 * @since 2025/6/5 17:12
 */
interface ExpboostQwQAPI {
    /** 获取语言文件接口 **/
    fun getLanguage(): ExpboostQwQLanguage

    /** 获取数据接口 **/
    fun getDatabase(): ExpboostQwQDatabase
}