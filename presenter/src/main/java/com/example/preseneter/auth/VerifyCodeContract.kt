package com.example.preseneter.auth

import com.example.core.module.auth.VerifyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface VerifyCodeContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
        val sideEffect: Flow<String>

    }

    interface Direction {
        suspend fun openCreateCode()
    }

    sealed interface Intent {
        data object OpenCreateCode : Intent
        data class VerifyCode(val code: VerifyData) : Intent
    }

    data class UiState(
        var isLoading: Boolean = false,
        var errorMessage: String? = null
    )
}
