package com.example.usecase.usecase.repors

import androidx.paging.PagingData
import com.example.core.module.Transaction
import kotlinx.coroutines.flow.Flow

interface ReportsUseCase {
    fun getTransferHistory(size: Int, currentPage: Int): Flow<PagingData<Transaction>>
}