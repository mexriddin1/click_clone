package com.example.preseneter.pin_code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preseneter.ScreenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterCodeViewModel @Inject constructor(
    private val direction: EnterCodeContract.Direction,
    private val manager: ScreenManager
) : ViewModel(), EnterCodeContract.ViewModel {

    override var uiState = MutableStateFlow(EnterCodeContract.UiState())

    override fun onAction(intent: EnterCodeContract.Intent) {
        when (intent) {
            is EnterCodeContract.Intent.OpenMainScreen -> {
                viewModelScope.launch {
                    direction.openMainScreen()
                }
            }

            is EnterCodeContract.Intent.OpenLoginScreen -> {
                viewModelScope.launch {
                    manager.register = false
                    manager.createPinCode = false
                    direction.openLoginScreen()
                }
            }

            is EnterCodeContract.Intent.OpenCreateScreen -> {
                viewModelScope.launch {
                    manager.createPinCode = false
                    direction.openCreateScreen()
                }
            }

            is EnterCodeContract.Intent.CheckPinScreen -> {
                manager.checkPinCode(intent.code).onEach {
                    it.onSuccess {
                        onAction(EnterCodeContract.Intent.OpenMainScreen)
                    }

                    it.onFailure { error ->
                        uiState.value.errorMessage = error.message.toString()
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}
