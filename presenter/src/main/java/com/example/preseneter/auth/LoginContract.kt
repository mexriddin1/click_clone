package com.example.preseneter.auth

import com.example.core.module.auth.LoginData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LoginContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
        val sideEffect: Flow<String>
    }

    interface Direction {
        suspend fun openRegister()
        suspend fun openVerify()
    }

    sealed interface Intent {
        data object OpenRegister : Intent
        data object OpenVerify : Intent
        data class LoginUser(val data: LoginData) : Intent
    }

    data class UiState(
        var isLoading: Boolean = false,
        var numberError: String? = null,
        var passwordError: String? = null
    )
}
