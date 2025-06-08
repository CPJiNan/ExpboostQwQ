package org.littlesheep.expboostQwQ

import org.littlesheep.expboostQwQ.data.Database
import org.littlesheep.expboostQwQ.data.DefaultJsonDatabase
import org.littlesheep.expboostQwQ.data.DefaultSqlDatabase
import org.littlesheep.expboostQwQ.data.DefaultSqliteDatabase
import org.littlesheep.expboostQwQ.utils.FileUtils
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.PlatformFactory
import taboolib.library.configuration.ConfigurationSection
import taboolib.platform.util.bukkitPlugin
import java.io.File

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ
 *
 * @author LittleSheep, 季楠
 * @since 2025/6/5 17:12
 */
object DefaultExpboostQwQDatabase : ExpboostQwQDatabase {
    private var database: Database? = null

    /** 获取默认数据库实例 **/
    override fun getDefault(): Database {
        return database ?: error("ExpboostQwQ database has not finished loading, or failed to load!")
    }

    /** 获取新的 Json 数据库实例 **/
    override fun getJson(): DefaultJsonDatabase {
        return DefaultJsonDatabase()
    }

    /** 获取新的 Json 数据库实例 **/
    override fun getJson(contents: String): DefaultJsonDatabase {
        return DefaultJsonDatabase(contents)
    }

    /** 获取新的 Json 数据库实例 **/
    override fun getJson(file: File): DefaultJsonDatabase {
        return DefaultJsonDatabase(file)
    }

    /** 获取新的 Sql 数据库实例 **/
    override fun getSql(section: ConfigurationSection, table: String): DefaultSqlDatabase {
        return DefaultSqlDatabase(section, table)
    }

    /** 获取新的 Sqlite 数据库实例 **/
    override fun getSqlite(file: File, table: String): DefaultSqliteDatabase {
        return DefaultSqliteDatabase(file, table)
    }

    @Awake(LifeCycle.LOAD)
    fun onLoad() {
        val settings = ExpboostQwQSettings.settings
        database = when (ExpboostQwQSettings.databaseType) {
            "JSON" -> {
                val file = settings.getString("Database.JSON.file", "database.json")!!
                getJson(FileUtils.getFileOrCreate(file))
            }

            "SQL" -> {
                val section = settings.getConfigurationSection("Database.SQL")!!
                val table = settings.getString("Database.SQL.table", bukkitPlugin.name)!!
                getSql(section, table)
            }

            "SQLITE" -> {
                val file = settings.getString("Database.SQLITE.file", "database.db")!!
                val table = settings.getString("Database.SQLITE.table", bukkitPlugin.name)!!
                getSqlite(FileUtils.getFileOrCreate(file), table)
            }

            else -> null
        }
    }

    @Awake(LifeCycle.CONST)
    fun onConst() {
        PlatformFactory.registerAPI<ExpboostQwQDatabase>(DefaultExpboostQwQDatabase)
    }
}