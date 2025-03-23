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
class CardViewModel @Inject constructor(
    private val direction: CardContract.Direction,
    private val cardUseCase: CardUseCase
) : ViewModel(), CardContract.ViewModel {

    override var uiState = MutableStateFlow(CardContract.UiState())

    private inline fun reduce(block: (CardContract.UiState) -> CardContract.UiState) {
        val old = uiState.value
        val new = block(old)
        uiState.value = new
    }

    init {
        reduce { it.copy(isLoading = true) }
        cardUseCase.getAllCard().onEach {
            it.onSuccess { list ->
                reduce { it.copy(cardList = list) }
                reduce { it.copy(isLoading = false) }
            }

            it.onFailure {
                reduce { it.copy(isLoading = false) }
            }
        }.launchIn(viewModelScope)
    }

    override fun onAction(intent: CardContract.Intent) {
        when (intent) {
            is CardContract.Intent.OpenAddCard -> {
                viewModelScope.launch {
                    direction.openAddCard()
                }
            }

            is CardContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
        }
    }
}
