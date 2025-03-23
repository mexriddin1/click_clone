package com.example.preseneter.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecase.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val direction: LoginContract.Direction,
    private val authUseCase: AuthUseCase
) : ViewModel(), LoginContract.ViewModel {
    override var uiState = MutableStateFlow(LoginContract.UiState())
    private val _sideEffect = Channel<String>()
    override val sideEffect = _sideEffect.receiveAsFlow()

    private inline fun reduce(block: (LoginContract.UiState) -> LoginContract.UiState) {
        val old = uiState.value
        val new = block(old)
        uiState.value = new
    }

    override fun onAction(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.OpenRegister -> {
                viewModelScope.launch {
                    direction.openRegister()
                }
            }

            is LoginContract.Intent.OpenVerify -> {
                viewModelScope.launch {
                    direction.openVerify()
                }
            }

            is LoginContract.Intent.LoginUser -> {
                reduce { it.copy(isLoading = true) }
                authUseCase.loginUser(intent.data).onEach {
                    it.onSuccess {
                        reduce { it.copy(isLoading = false) }
                        onAction(LoginContract.Intent.OpenVerify)
                    }

                    it.onFailure { error ->
                        reduce { it.copy(isLoading = false) }
                        _sideEffect.send(error.message.toString())
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

}
