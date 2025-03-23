package com.example.preseneter.transfer

import com.example.core.module.card.CardDate
import com.example.entity.remote.transfer.request.TransactionRequest
import kotlinx.coroutines.flow.StateFlow

interface UserContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
    }

    interface Direction {
        suspend fun verifyScreen()
        suspend fun back()
    }

    sealed interface Intent {
        data class TransferSum(val data: TransactionRequest) : Intent
        data object OpenVerify : Intent
        data object Back : Intent
    }

    data class UiState(
        var isLoading: Boolean = false,
        var isLoadingCard: Boolean = false,
        var cardList: List<CardDate> = listOf()

    )

}
