package com.example.preseneter.pin_code

import kotlinx.coroutines.flow.StateFlow

interface EnterCodeContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
    }

    interface Direction {
        suspend fun openLoginScreen()
        suspend fun openMainScreen()
        suspend fun openCreateScreen()
    }

    sealed interface Intent {
        data object OpenLoginScreen : Intent
        data object OpenMainScreen : Intent
        data object OpenCreateScreen : Intent
        data class CheckPinScreen(val code: String) : Intent
    }

    data class UiState(
        var errorMessage: String = ""
    )
}
