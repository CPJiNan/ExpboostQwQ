package org.littlesheep.expboostQwQ.command.subcommand

import org.littlesheep.expboostQwQ.manager.BoosterManager
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.subCommand

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ.command.subcommand
 *
 * @author 季楠
 * @since 2025/6/9 21:02
 */
object APICommand {
    val api = subCommand {
        literal("booster") {
            literal("addBooster").dynamic("multiplier").dynamic("endTime").dynamic("levelGroup").dynamic("source") {
                execute<ProxyCommandSender> { _, context, _ ->
                    BoosterManager.addBooster(
                        context["multiplier"].toDouble(),
                        context["endTime"].toDouble().toLong(),
                        context["levelGroup"],
                        context["source"]
                    )
                }
            }
            literal("removeBooster").dynamic("id") {
                execute<ProxyCommandSender> { _, context, _ ->
                    BoosterManager.removeBooster(context["id"])
                }
            }
            literal("getBooster").dynamic("id") {
                execute<ProxyCommandSender> { sender, context, _ ->
                    sender.sendMessage(
                        BoosterManager.getBooster(context["id"]).toString()
                    )
                }
            }
            literal("getBoosters") {
                execute<ProxyCommandSender> { sender, context, _ ->
                    sender.sendMessage(
                        BoosterManager.getBoosters().toString()
                    )
                }
            }
            literal("addPlayerBooster").dynamic("player").dynamic("id") {
                execute<ProxyCommandSender> { _, context, _ ->
                    BoosterManager.addPlayerBooster(context["player"], context["id"])
                }
            }
            literal("removePlayerBooster").dynamic("player").dynamic("id") {
                execute<ProxyCommandSender> { _, context, _ ->
                    BoosterManager.removePlayerBooster(context["player"], context["id"])
                }
            }
            literal("getPlayerBoosters").dynamic("player") {
                execute<ProxyCommandSender> { sender, context, _ ->
                    sender.sendMessage(
                        BoosterManager.getPlayerBoosters(context["player"]).toString()
                    )
                }
            }
        }
    }
}