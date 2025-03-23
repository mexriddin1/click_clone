package com.example.entity.local

import android.content.Context
import android.content.SharedPreferences
import com.example.core.module.main.Language
import java.util.Locale

object LocationHelper {
    private lateinit var pref: SharedPreferences

    fun attach(context: Context): Context {
        pref = context.getSharedPreferences("Language", Context.MODE_PRIVATE)
        val lang = pref.getString("LANGUAGE", "uz") ?: "uz"
        return setLocation(context, lang)
    }

    fun setLocation(context: Context, lang: String): Context {
        pref.edit().putString("LANGUAGE", lang).apply()
        updateResource(context, lang)
        return context
    }

    fun getLocation(): Language {
        return when (pref.getString("LANGUAGE", "uz") ?: "uz") {
            "en" -> Language.ENG
            "ru" -> Language.RUS
            else -> Language.UZB
        }
    }

    private fun updateResource(context: Context, lang: String) {
        val local = Locale(lang)
        val resources = context.resources

        val configuration = resources.configuration
        configuration.setLocale(local)
        configuration.setLayoutDirection(local)

        context.createConfigurationContext(configuration)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

}