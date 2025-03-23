package com.example.preseneter.select_language

import android.content.Context
import kotlinx.coroutines.flow.StateFlow

interface SelectLanguageContract {
    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
        suspend fun autoChangeLanguage(context: Context)
    }

    interface Direction {
        suspend fun openLogin()
    }

    sealed interface Intent {
        data class ClickItem(val language: com.example.core.module.main.Language, val context: Context) :
            Intent
    }

    data class UiState(
        var selectText: String = ""
    )
}