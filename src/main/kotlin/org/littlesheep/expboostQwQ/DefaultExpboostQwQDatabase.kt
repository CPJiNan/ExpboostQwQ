package org.littlesheep.expboostQwQ

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.PlatformFactory

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ
 *
 * @author LittleSheep, 季楠
 * @since 2025/6/5 17:12
 */
object DefaultExpboostQwQDatabase : ExpboostQwQDatabase {
    @Awake(LifeCycle.CONST)
    fun onConst() {
        PlatformFactory.registerAPI<ExpboostQwQDatabase>(DefaultExpboostQwQDatabase)
    }
}