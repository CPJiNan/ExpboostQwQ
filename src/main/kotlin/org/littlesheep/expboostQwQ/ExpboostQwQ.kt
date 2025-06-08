package org.littlesheep.expboostQwQ

import taboolib.common.LifeCycle
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.disablePlugin
import taboolib.common.platform.function.registerLifeCycleTask
import taboolib.platform.BukkitPlugin

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ
 *
 * @author LittleSheep, 季楠
 * @since 2025/6/5 17:12
 */
object ExpboostQwQ : Plugin() {
    val plugin by lazy { BukkitPlugin.getInstance() }
    private var api: ExpboostQwQAPI? = null

    init {
        registerLifeCycleTask(LifeCycle.INIT) {
            try {
                ExpboostQwQLoader.startup()
            } catch (ex: Throwable) {
                ex.printStackTrace()
                disablePlugin()
            }
        }
    }

    /** 获取开发者接口 **/
    fun api(): ExpboostQwQAPI {
        return api ?: error("ExpboostQwQAPI has not finished loading, or failed to load!")
    }

    /** 注册开发者接口 **/
    fun register(api: ExpboostQwQAPI) {
        ExpboostQwQ.api = api
    }
}