package com.example.preseneter.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preseneter.ScreenManager
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
class VerifyCodeViewModel @Inject constructor(
    private val direction: VerifyCodeContract.Direction,
    private val authUseCase: AuthUseCase,
    private val manager: ScreenManager
) : ViewModel(), VerifyCodeContract.ViewModel {

    override var uiState = MutableStateFlow(VerifyCodeContract.UiState())
    private val _sideEffect = Channel<String>()
    override val sideEffect = _sideEffect.receiveAsFlow()

    private fun reduce(block: (VerifyCodeContract.UiState) -> VerifyCodeContract.UiState) {
        val old = uiState.value
        val new = block.invoke(old)
        uiState.value = new
    }

    override fun onAction(intent: VerifyCodeContract.Intent) {
        when (intent) {

            is VerifyCodeContract.Intent.OpenCreateCode -> {
                manager.register = true
                viewModelScope.launch {
                    direction.openCreateCode()
                }
            }

            is VerifyCodeContract.Intent.VerifyCode -> {
                reduce {
                    it.copy(isLoading = true)
                }

                authUseCase.loginVerifyCode(intent.code).onEach {
                    it.onSuccess {
                        reduce { it.copy(isLoading = false) }
                        uiState.value.errorMessage = ""
                        onAction(VerifyCodeContract.Intent.OpenCreateCode)
                    }

                    it.onFailure { error ->
                        reduce { it.copy(isLoading = false) }
                        uiState.value.errorMessage = error.message.toString()
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}
