package org.littlesheep.expboostQwQ

import org.littlesheep.expboostQwQ.data.Database
import org.littlesheep.expboostQwQ.data.DefaultJsonDatabase
import org.littlesheep.expboostQwQ.data.DefaultSqlDatabase
import org.littlesheep.expboostQwQ.data.DefaultSqliteDatabase
import taboolib.library.configuration.ConfigurationSection
import java.io.File

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ
 *
 * @author LittleSheep, 季楠
 * @since 2025/6/6 18:47
 */
interface ExpboostQwQDatabase {
    /** 获取默认数据库实例 **/
    fun getDefault(): Database

    /** 获取新的 Json 数据库实例 **/
    fun getJson(): DefaultJsonDatabase

    /** 获取新的 Json 数据库实例 **/
    fun getJson(file: File): DefaultJsonDatabase

    /** 获取新的 Json 数据库实例 **/
    fun getJson(contents: String): DefaultJsonDatabase

    /** 获取新的 Sql 数据库实例 **/
    fun getSql(section: ConfigurationSection, table: String): DefaultSqlDatabase

    /** 获取新的 Sqlite 数据库实例 **/
    fun getSqlite(file: File, table: String): DefaultSqliteDatabase
}