package com.example.preseneter.card


import com.example.core.module.card.CardDate
import kotlinx.coroutines.flow.StateFlow

interface CardContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
    }

    interface Direction {
        suspend fun openAddCard()
        suspend fun back()
    }

    sealed interface Intent {
        data object OpenAddCard : Intent
        data object Back : Intent
    }

    data class UiState(
        var isLoading: Boolean = false,
        var cardList: List<CardDate> = listOf()
    )
}
