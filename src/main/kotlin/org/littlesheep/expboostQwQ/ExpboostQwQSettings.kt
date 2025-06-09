package org.littlesheep.expboostQwQ

import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigNode
import taboolib.module.configuration.Configuration

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ
 *
 * @author LittleSheep, 季楠
 * @since 2025/6/5 17:12
 */
@ConfigNode(bind = "settings.yml")
object ExpboostQwQSettings {
    @Config("settings.yml")
    lateinit var settings: Configuration
        private set

    /** 语言 **/
    @ConfigNode("Options.Language")
    var language = "zh_CN"

    /** 配置文件版本 **/
    @ConfigNode("Options.Config-Version")
    var configVersion = -1

    /** 检查版本更新 **/
    @ConfigNode("Options.Check-Update")
    var checkUpdate = true

    /** OP 版本更新通知 **/
    @ConfigNode("Options.OP-Notify")
    var opNotify = true

    /** bStats 统计 **/
    @ConfigNode("Options.Send-Metrics")
    var sendMetrics = true

    /** 调试模式 **/
    @ConfigNode("Options.Debug")
    var debug = false

    /** 数据存储方式 **/
    @ConfigNode("Database.Type")
    var databaseType = "SQLITE"

    /** 数据存储方式 **/
    @ConfigNode("Booster.Calculation")
    var boosterCalculation = "highest"
}