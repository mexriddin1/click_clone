package com.example.preseneter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.module.Transaction
import com.example.usecase.usecase.repors.ReportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val direction: ReportsContract.Direction,
    private val reportsRepository: ReportsUseCase
) : ViewModel(), ReportsContract.ViewModel {

    override var uiState = MutableStateFlow(ReportsContract.UiState())

    init {
        onAction(ReportsContract.Intent.GetAllData)
    }

    private fun reduce(block: (ReportsContract.UiState) -> ReportsContract.UiState) {
        val old = uiState.value
        val new = block.invoke(old)
        uiState.value = new
    }

    override fun onAction(intent: ReportsContract.Intent) {
        when (intent) {
            is ReportsContract.Intent.GetAllData -> {

            }
        }
    }

    override fun getHistory(size: Int, pageCount: Int): Flow<PagingData<Transaction>> =
        reportsRepository.getTransferHistory(size = size, currentPage = pageCount)
            .cachedIn(viewModelScope)
}
