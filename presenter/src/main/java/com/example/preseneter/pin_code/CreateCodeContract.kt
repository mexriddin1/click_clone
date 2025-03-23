package com.example.preseneter.pin_code

import kotlinx.coroutines.flow.StateFlow

interface CreateCodeContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
    }

    interface Direction {
        suspend fun openLoginScreen()
        suspend fun openEnterScreen()
    }

    sealed interface Intent {
        data object OpenEnterCode : Intent
        data object OpenLoginScreen : Intent
        data class SetPinCode(val code: String) : Intent
    }

    data class UiState(
        var errorMessage: String = ""
    )
}
