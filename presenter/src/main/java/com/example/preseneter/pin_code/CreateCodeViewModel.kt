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
class CreateCodeViewModel @Inject constructor(
    private val direction: CreateCodeContract.Direction,
    private val manager: ScreenManager
) : ViewModel(), CreateCodeContract.ViewModel {

    override var uiState = MutableStateFlow(CreateCodeContract.UiState())

    override fun onAction(intent: CreateCodeContract.Intent) {
        when (intent) {
            is CreateCodeContract.Intent.OpenEnterCode -> {
                viewModelScope.launch {
                    manager.createPinCode = true
                    direction.openEnterScreen()
                }
            }

            is CreateCodeContract.Intent.OpenLoginScreen -> {
                viewModelScope.launch {
                    manager.createPinCode = false
                    manager.register = false
                    direction.openLoginScreen()
                }
            }

            is CreateCodeContract.Intent.SetPinCode -> {
                manager.setNewPinCode(intent.code).onEach {
                    it.onSuccess {
                        onAction(CreateCodeContract.Intent.OpenEnterCode)
                    }

                    it.onFailure {

                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}
