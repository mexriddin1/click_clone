package com.example.preseneter.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecase.usecase.card.CardUseCase
import com.example.usecase.usecase.transfers.TransferUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val direction: UserContract.Direction,
    private val transferUseCase: TransferUseCase,
    private val cardUseCase: CardUseCase
) : ViewModel(), UserContract.ViewModel {

    override var uiState = MutableStateFlow(UserContract.UiState())

    private fun reduce(block: (UserContract.UiState) -> UserContract.UiState) {
        val old = uiState.value
        val new = block.invoke(old)
        uiState.value = new
    }

    init {
        reduce { it.copy(isLoadingCard = true) }
        cardUseCase.getAllCard().onEach {
            it.onSuccess { list ->
                reduce { it.copy(cardList = list) }
                reduce { it.copy(isLoadingCard = false) }
            }

            it.onFailure {
                reduce { it.copy(isLoadingCard = false) }
            }
        }.launchIn(viewModelScope)
    }

    override fun onAction(intent: UserContract.Intent) {
        when (intent) {
            is UserContract.Intent.TransferSum -> {
                reduce { it.copy(isLoading = true) }
                transferUseCase.transfer(intent.data).onEach {
                    it.onSuccess {
                        reduce { it.copy(isLoading = false) }
                        onAction(UserContract.Intent.OpenVerify)
                    }

                    it.onFailure {
                        reduce { it.copy(isLoading = false) }
                    }
                }.launchIn(viewModelScope)
            }

            is UserContract.Intent.OpenVerify -> {
                viewModelScope.launch {
                    direction.verifyScreen()
                }
            }

            is UserContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
        }
    }
}
