package com.example.preseneter.transfer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entity.remote.transfer.request.GetPanRequest
import com.example.usecase.usecase.transfers.TransferUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val direction: TransferContract.Direction,
    private val transferUseCase: TransferUseCase
) : ViewModel(), TransferContract.ViewModel {

    override var uiState = MutableStateFlow(TransferContract.UiState())

    private fun reduce(block: (TransferContract.UiState) -> TransferContract.UiState) {
        val old = uiState.value
        val new = block.invoke(old)
        uiState.value = new
    }

    override fun onAction(intent: TransferContract.Intent) {
        when (intent) {
            is TransferContract.Intent.OpenUser -> {
                viewModelScope.launch {
                    direction.openUserScreen(intent.user)
                }
            }

            is TransferContract.Intent.SearchUser -> {
                reduce { it.copy(user = listOf()) }
                reduce { it.copy(isLoading = true) }
                transferUseCase.getPan((GetPanRequest(intent.pan))).onEach {
                    it.onSuccess { data ->
                        reduce {
                            it.copy(
                                user = listOf(
                                    com.example.core.module.transfer.TransferUser(
                                        data.pan,
                                        intent.pan
                                    )
                                )
                            )
                        }
                        reduce { it.copy(isLoading = false) }
                    }

                    it.onFailure {
                        Log.d("BBB", "onAction: ${it.message}.")
                        reduce { it.copy(isLoading = false) }
                        reduce { it.copy(isFound = false) }

                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}
