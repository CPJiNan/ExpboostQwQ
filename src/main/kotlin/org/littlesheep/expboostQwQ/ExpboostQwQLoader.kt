package org.littlesheep.expboostQwQ

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.Platform
import taboolib.common.platform.function.console
import taboolib.common.util.unsafeLazy
import taboolib.module.lang.sendLang
import taboolib.module.metrics.Metrics
import taboolib.platform.BukkitPlugin

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ
 *
 * @author LittleSheep, 季楠
 * @since 2025/6/5 17:12
 */
object ExpboostQwQLoader {
    val api by unsafeLazy { DefaultExpboostQwQAPI() }

    /** 启动 ExpboostQwQ 服务 **/
    fun startup() {
        ExpboostQwQ.register(api)
    }

    @Awake(LifeCycle.LOAD)
    fun onLoad() {
        if (ExpboostQwQSettings.sendMetrics) {
            Metrics(
                18992,
                BukkitPlugin.getInstance().description.version,
                Platform.BUKKIT
            )
            Metrics(
                25432,
                BukkitPlugin.getInstance().description.version,
                Platform.BUKKIT
            )
        }
    }

    @Awake(LifeCycle.ENABLE)
    fun onEnable() {
        console().sendLang("Plugin-Enabled")
    }

    @Awake(LifeCycle.DISABLE)
    fun onDisable() {
        console().sendLang("Plugin-Disable")
    }
}