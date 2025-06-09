package org.littlesheep.expboostQwQ.data

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ.data
 *
 * @author 季楠
 * @since 2025/6/9 19:30
 */
data class BoosterData(
    val id: String,
    val multiplier: Double = 0.0,
    val endTime: Long = -1,
    val levelGroup: String = "",
    val source: String = ""
)