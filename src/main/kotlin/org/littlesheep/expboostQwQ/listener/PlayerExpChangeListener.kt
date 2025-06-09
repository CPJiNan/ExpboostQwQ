package org.littlesheep.expboostQwQ.listener

import com.github.cpjinan.plugin.akarilevel.common.event.exp.PlayerExpChangeEvent
import org.littlesheep.expboostQwQ.ExpboostQwQSettings
import org.littlesheep.expboostQwQ.manager.BoosterManager
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.sendLang
import kotlin.math.roundToLong

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ.listener
 *
 * @author 季楠
 * @since 2025/6/9 21:31
 */
object PlayerExpChangeListener {
    @SubscribeEvent
    fun onPlayerExpChange(event: PlayerExpChangeEvent) {
        val player = event.player
        val expAmount = event.expAmount
        val levelGroup = event.levelGroup
        val source = event.source
        val uuid = player.uniqueId.toString()

        BoosterManager.removeInvalidPlayerBoosters(uuid)

        var boosters = BoosterManager.getPlayerBoosters(uuid)
        if (boosters.isEmpty()) return

        boosters = boosters.filter {
            it.levelGroup.isEmpty() || it.levelGroup == levelGroup
        }
        if (boosters.isEmpty()) return

        boosters = boosters.filter {
            it.source.isEmpty() || it.source == source
        }
        if (boosters.isEmpty()) return

        val multiplier = when (ExpboostQwQSettings.boosterCalculation) {
            "highest" -> {
                boosters.maxBy { it.multiplier }.multiplier
            }

            "add" -> {
                boosters.sumOf { it.multiplier }
            }

            "multiply" -> {
                boosters.fold(1.0) { i, booster -> i * (1 + booster.multiplier) } - 1
            }

            else -> 0.0
        }

        val boostedExpAmount = event.expAmount + (expAmount * multiplier).roundToLong()
        player.sendLang("Exp-Boosted", event.expAmount, boostedExpAmount, multiplier)

        event.expAmount = boostedExpAmount
    }
}