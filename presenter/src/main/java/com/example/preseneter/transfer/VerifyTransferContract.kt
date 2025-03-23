package com.example.preseneter.transfer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface VerifyTransferContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
        val message: Flow<String>
    }

    interface Direction {
        suspend fun back()
    }

    sealed interface Intent {
        data class CheckCode(val code: String) : Intent
        data object Back : Intent
    }

    data class UiState(
        var isLoading: Boolean = false,
        var message: String = ""
    )
}
