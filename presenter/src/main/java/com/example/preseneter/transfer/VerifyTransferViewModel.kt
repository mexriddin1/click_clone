package com.example.preseneter.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecase.usecase.transfers.TransferUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyTransferViewModel @Inject constructor(
    private val direction: VerifyTransferContract.Direction,
    private val transferUseCase: TransferUseCase
) : ViewModel(), VerifyTransferContract.ViewModel {

    override var uiState = MutableStateFlow(VerifyTransferContract.UiState())
    private val _message = Channel<String>()

    override val message: Flow<String> = _message.receiveAsFlow()

    private fun reduce(block: (VerifyTransferContract.UiState) -> VerifyTransferContract.UiState) {
        val old = uiState.value
        val new = block.invoke(old)
        uiState.value = new
    }

    override fun onAction(intent: VerifyTransferContract.Intent) {
        when (intent) {
            is VerifyTransferContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }

            is VerifyTransferContract.Intent.CheckCode -> {
                reduce { it.copy(message = "", isLoading = true) }
                transferUseCase.verifyTransfer(intent.code).onEach {
                    it.onSuccess {
                        _message.send("To'lov amalga oshirildi")
                        onAction(VerifyTransferContract.Intent.Back)
                        reduce { it.copy(isLoading = false) }
                    }
                    it.onFailure {
                        reduce { it.copy(message = it.message, isLoading = false) }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

}
