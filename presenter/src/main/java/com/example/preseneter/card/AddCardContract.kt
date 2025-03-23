package com.example.preseneter.card

import com.example.core.module.card.NewCardDate
import kotlinx.coroutines.flow.StateFlow

interface AddCardContract {
    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
    }

    interface Direction {
        suspend fun back()
    }

    sealed interface Intent {
        data object Back : Intent
        data class AddNewCard(val card: NewCardDate) : Intent
    }

    data class UiState(
        var isLoading: Boolean = false
    )
}
