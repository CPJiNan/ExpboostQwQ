package org.littlesheep.expboostQwQ

import org.bukkit.command.CommandSender
import org.littlesheep.expboostQwQ.utils.FileUtils
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.PlatformFactory
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.lang.Language
import taboolib.module.lang.event.PlayerSelectLocaleEvent
import taboolib.module.lang.event.SystemSelectLocaleEvent
import taboolib.module.lang.registerLanguage
import taboolib.platform.util.asLangTextList
import taboolib.platform.util.asLangTextOrNull
import taboolib.platform.util.sendLang
import java.util.*

/**
 * ExpboostQwQ
 * org.littlesheep.expboostQwQ
 *
 * @author LittleSheep, 季楠
 * @since 2025/6/5 17:12
 */
object DefaultExpboostQwQLanguage : ExpboostQwQLanguage {
    /** 发送语言文本 **/
    override fun sendLang(sender: CommandSender, key: String, vararg args: Any) {
        sender.sendLang(key, *args)
    }

    /** 获取语言文本 **/
    override fun getLang(sender: CommandSender, key: String, vararg args: Any): String? {
        return sender.asLangTextOrNull(key, *args)
    }

    /** 获取语言文本 **/
    override fun getLangList(sender: CommandSender, key: String, vararg args: Any): List<String> {
        return sender.asLangTextList(key, *args)
    }

    /** 释放 i18n 资源 **/
    override fun releaseResource() {
        fun getLocale(): String {
            val locale = Locale.getDefault()
            val language = locale.language.lowercase()
            val country = if (locale.country.isNotEmpty()) locale.country.uppercase() else language.uppercase()
            return "${language}_$country"
        }
    }

    /** 重载语言文件 **/
    override fun reload() {
        Language.reload()
    }

    @SubscribeEvent
    fun onPlayerSelectLocale(event: PlayerSelectLocaleEvent) {
        val lang = ExpboostQwQSettings.language
        val isLangExist = lang in Language.languageCode
        val langFile = FileUtils.getFileOrNull("lang/$lang.yml")

        if (langFile != null) {
            if (!isLangExist) {
                registerLanguage(lang)
            }
            event.locale = lang
        } else {
            event.locale = "zh_CN"
        }
    }

    @SubscribeEvent
    fun onSystemSelectLocale(event: SystemSelectLocaleEvent) {
        val lang = ExpboostQwQSettings.language
        val isLangExist = lang in Language.languageCode
        val langFile = FileUtils.getFileOrNull("lang/$lang.yml")

        if (langFile != null) {
            if (!isLangExist) {
                registerLanguage(lang)
            }
            event.locale = lang
        } else {
            event.locale = "zh_CN"
        }
    }

    @Awake(LifeCycle.CONST)
    fun onConst() {
        PlatformFactory.registerAPI<ExpboostQwQLanguage>(DefaultExpboostQwQLanguage)
    }
}