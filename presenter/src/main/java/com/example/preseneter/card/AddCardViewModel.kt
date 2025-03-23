package com.example.preseneter.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecase.usecase.card.CardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val direction: AddCardContract.Direction,
    private val cardUseCase: CardUseCase
) : ViewModel(), AddCardContract.ViewModel {

    override var uiState = MutableStateFlow(AddCardContract.UiState())

    private inline fun reduce(block: (AddCardContract.UiState) -> AddCardContract.UiState) {
        val old = uiState.value
        val new = block(old)
        uiState.value = new
    }

    override fun onAction(intent: AddCardContract.Intent) {
        when (intent) {
            is AddCardContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }

            is AddCardContract.Intent.AddNewCard -> {
                reduce {
                    it.copy(isLoading = true)
                }
                cardUseCase.addNewCard(intent.card).onEach {
                    it.onSuccess {
                        reduce {
                            it.copy(isLoading = false)
                        }
                    }

                    it.onFailure {
                        reduce {
                            it.copy(isLoading = false)
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}
