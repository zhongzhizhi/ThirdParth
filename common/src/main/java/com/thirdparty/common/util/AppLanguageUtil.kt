package com.thirdparty.common.util

import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import com.dosmono.common.preference.PreferenceKey
import com.dosmono.logger.Logger
import com.thirdparty.common.app.GlobalApplication
import com.thirdparty.common.preference.Preference
import java.util.*


/**
 *  @项目名：  MeetingApp
 *  @包名：    com.dosmono.common.util
 *  @文件名:   AppLanguageUtil
 *  @创建者:   zer
 *  @创建时间:  2020/12/2 17:16
 *  @描述：    TODO
 */
object AppLanguageUtil {
    private val mLocalLanguage by Preference(PreferenceKey.APP_LANGUAGE,"")

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun switchLanguage(baseContext: Context, locale: Locale){
        val resource = baseContext.resources
        val config = resource.configuration
        config.setLocale(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocales(LocaleList(locale))
        }
        baseContext.createConfigurationContext(config)
    }

    fun switchAppLanguage(locale: Locale){
        val resource = GlobalApplication.getContext().resources
        val configuration = resource.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale)
        }else{
            configuration.locale = locale
        }
        resource.updateConfiguration(configuration,resource.displayMetrics)
    }

    fun attachBaseContext(content: Context?): Context?{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            return content?.let {
                val config = it.resources.configuration
                val locale = when(mLocalLanguage){
                    "zh" -> {
                        Locale.SIMPLIFIED_CHINESE
                    }
                    "en" -> {
                        Locale.ENGLISH
                    }
                    "ja" -> {
                        Locale.JAPANESE
                    }
                    else -> {
                        Locale.getDefault()
                    }
                }
                config.setLocale(locale)
                config.setLocales(LocaleList(locale))
                it.createConfigurationContext(config)
            }
        }
        return content
    }

    fun getLocal(): Locale{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            GlobalApplication.getContext().resources.configuration.locales[0]
        }else{
            GlobalApplication.getContext().resources.configuration.locale
        }
    }
}