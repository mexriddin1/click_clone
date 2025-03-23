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
class RegisterViewModel @Inject constructor(
    private val direction: RegisterContract.Direction,
    private val authUseCase: AuthUseCase
) : ViewModel(), RegisterContract.ViewModel {

    override var uiState = MutableStateFlow(RegisterContract.UiState())
    private val _sideEffect = Channel<String>()
    override val sideEffect = _sideEffect.receiveAsFlow()

    private inline fun reduce(block: (RegisterContract.UiState) -> RegisterContract.UiState) {
        val old = uiState.value
        val new = block(old)
        uiState.value = new
    }

    override fun onAction(intent: RegisterContract.Intent) {
        when (intent) {
            is RegisterContract.Intent.OpenVerify -> {
                viewModelScope.launch {
                    direction.openVerify()
                }
            }

            is RegisterContract.Intent.RegisterUser -> {
                reduce { it.copy(isLoading = false) }
                authUseCase.registerUser(intent.user).onEach {
                    it.onSuccess {
                        reduce { it.copy(isLoading = false) }
                        onAction(RegisterContract.Intent.OpenVerify)
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
