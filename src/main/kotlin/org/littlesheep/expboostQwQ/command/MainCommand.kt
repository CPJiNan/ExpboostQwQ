package org.littlesheep.expboostQwQ.command

import org.littlesheep.expboostQwQ.ExpboostQwQ
import org.littlesheep.expboostQwQ.ExpboostQwQSettings
import org.littlesheep.expboostQwQ.event.PluginReloadEvent
import org.littlesheep.expboostQwQ.utils.LoggerUtils.debug
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.*
import taboolib.expansion.createHelper
import taboolib.module.lang.sendLang

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ.command
 *
 * @author 季楠
 * @since 2025/6/8 23:22
 */
@CommandHeader(
    name = "expboostqwq",
    permission = "ExpboostQwQ.command.use",
    permissionDefault = PermissionDefault.OP
)
object MainCommand {
    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody(
        permission = "ExpboostQwQ.command.reload.use",
        permissionDefault = PermissionDefault.OP
    )
    val reload = subCommand {
        execute<ProxyCommandSender> { sender, _, _ ->
            debug("&8[&3Expboost&bQwQ&8] &5调试&7#1 &8| &6触发插件重载命令，正在展示处理逻辑。")

            PluginReloadEvent.Pre().call()
            val start = System.currentTimeMillis()
            var time = start

            ExpboostQwQSettings.settings.reload()
            debug("&r| &b◈ &r#1 配置文件重载完成，用时 ${System.currentTimeMillis() - time}ms。")
            time = System.currentTimeMillis()

            val languageAPI = ExpboostQwQ.api().getLanguage()
            languageAPI.reload()
            debug("&r| &b◈ &r#1 语言文件重载完成，用时 ${System.currentTimeMillis() - time}ms。")
            time = System.currentTimeMillis()

            val databaseAPI = ExpboostQwQ.api().getDatabase()
            databaseAPI.getDefault().reload()
            debug("&r| &b◈ &r#1 数据库重载完成，用时 ${System.currentTimeMillis() - time}ms。")
            time = System.currentTimeMillis()

            debug("&r| &a◈ &r#1 插件重载完毕，总计用时 ${System.currentTimeMillis() - start}ms。")

            PluginReloadEvent.Post().call()
            sender.sendLang("Plugin-Reloaded")
        }
    }
}