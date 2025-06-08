package org.littlesheep.expboostQwQ

import taboolib.common.platform.PlatformFactory

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ
 *
 * @author LittleSheep, 季楠
 * @since 2025/6/5 17:12
 */
class DefaultExpboostQwQAPI : ExpboostQwQAPI {
    /** 语言文件接口 **/
    var localLanguage = PlatformFactory.getAPI<ExpboostQwQLanguage>()

    /** 数据接口 **/
    var localDatabase = PlatformFactory.getAPI<ExpboostQwQDatabase>()

    /** 获取语言文件接口 **/
    override fun getLanguage(): ExpboostQwQLanguage {
        return localLanguage
    }

    /** 获取数据接口 **/
    override fun getDatabase(): ExpboostQwQDatabase {
        return localDatabase
    }
}