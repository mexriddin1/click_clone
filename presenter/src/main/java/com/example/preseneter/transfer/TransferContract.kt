package com.example.preseneter.transfer// MyNameContract Template

import kotlinx.coroutines.flow.StateFlow

interface TransferContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
    }

    interface Direction {
        suspend fun openUserScreen(user: com.example.core.module.transfer.TransferUser)
    }

    sealed interface Intent {
        data class OpenUser(val user: com.example.core.module.transfer.TransferUser) : Intent
        data class SearchUser(val pan: String) : Intent
    }

    data class UiState(
        var isFound: Boolean = true,
        var isLoading: Boolean = false,
        var user: List<com.example.core.module.transfer.TransferUser> = listOf(),
    )
}
