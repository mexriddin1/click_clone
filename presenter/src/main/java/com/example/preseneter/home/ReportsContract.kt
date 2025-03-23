package com.example.preseneter.home

import androidx.paging.PagingData
import com.example.core.module.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ReportsContract {
    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onAction(intent: Intent)
        fun getHistory(size: Int, pageCount: Int): Flow<PagingData<Transaction>>
    }

    interface Direction {

    }

    sealed interface Intent {
        data object GetAllData : Intent
    }

    data class UiState(
        var isLoading: Boolean = false,
        var listInput: List<Transaction> = listOf(),
        var listOutput: List<Transaction> = listOf(),
    )
}
