package com.example.preseneter.auth

import com.example.core.module.auth.RegisterData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface RegisterContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
        val sideEffect: Flow<String>
    }

    interface Direction {
        suspend fun openVerify()
    }

    sealed interface Intent {
        data object OpenVerify : Intent
        data class RegisterUser(val user: RegisterData) : Intent
    }

    data class UiState(
        var surnameError: String? = null,
        var lastnameError: String? = null,
        var birthdayError: String? = null,
        var genderError: String? = null,
        var phoneError: String? = null,
        var passwordError: String? = null,
        var passwordAgainError: String? = null,
        var isLoading: Boolean = false
    )
}
