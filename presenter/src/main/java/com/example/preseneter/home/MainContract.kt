package com.example.preseneter.home

import kotlinx.coroutines.flow.StateFlow

interface MainContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
    }

    interface Direction {
        suspend fun openCard()
    }

    sealed interface Intent {
        data object OpenCardScreen : Intent
    }

    data class UiState(
        var test: String = ""
    )
}
