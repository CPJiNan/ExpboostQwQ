package org.littlesheep.expboostQwQ.manager

import org.littlesheep.expboostQwQ.ExpboostQwQ
import org.littlesheep.expboostQwQ.data.BoosterData
import java.util.*


/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ.manager
 *
 * @author 季楠
 * @since 2025/6/9 19:38
 */
object BoosterManager {
    fun addBooster(
        multiplier: Double = 0.0,
        endTime: Long = -1,
        levelGroup: String = "",
        source: String = ""
    ): BoosterData? {
        if (endTime != -1L && endTime <= System.currentTimeMillis()) return null

        val id = UUID.randomUUID().toString()
        val data = ExpboostQwQ.api().getDatabase().getDefault()

        data["Boosters"] = data.getStringList("Boosters").plus(id)
        data["Booster_${id}_Multiplier"] = multiplier
        data["Booster_${id}_EndTime"] = endTime
        data["Booster_${id}_LevelGroup"] = levelGroup
        data["Booster_${id}_Source"] = source

        return BoosterData(id, multiplier, endTime, levelGroup, source)
    }

    fun removeBooster(id: String) {
        val data = ExpboostQwQ.api().getDatabase().getDefault()
        val booster = data.getStringList("Boosters").toMutableList()
        booster.remove(id)

        data["Boosters"] = booster
        data["Booster_${id}_Multiplier"] = null
        data["Booster_${id}_EndTime"] = null
        data["Booster_${id}_LevelGroup"] = null
        data["Booster_${id}_Source"] = null
    }

    fun getBooster(id: String): BoosterData? {
        val data = ExpboostQwQ.api().getDatabase().getDefault()

        if (id !in data.getStringList("Boosters")) return null

        return BoosterData(
            id,
            data.getDouble("Booster_${id}_Multiplier", 0.0),
            data.getLong("Booster_${id}_EndTime", -1),
            data.getString("Booster_${id}_LevelGroup", "")!!,
            data.getString("Booster_${id}_Source", "")!!
        )
    }

    fun getBoosters(): List<BoosterData> {
        val data = ExpboostQwQ.api().getDatabase().getDefault()
        return data.getStringList("Boosters").mapNotNull { getBooster(it) }
    }

    fun addPlayerBooster(player: String, id: String) {
        val data = ExpboostQwQ.api().getDatabase().getDefault()
        data["Player_${player}_Boosters"] = data.getStringList("Player_${player}_Boosters").plus(id)
    }

    fun removePlayerBooster(player: String, id: String) {
        val data = ExpboostQwQ.api().getDatabase().getDefault()
        val booster = data.getStringList("Player_${player}_Boosters").toMutableList()
        booster.remove(id)
        data["Player_${player}_Boosters"] = booster
    }

    fun getPlayerBoosters(player: String): List<BoosterData> {
        val data = ExpboostQwQ.api().getDatabase().getDefault()
        return data.getStringList("Player_${player}_Boosters").mapNotNull { getBooster(it) }
    }
}