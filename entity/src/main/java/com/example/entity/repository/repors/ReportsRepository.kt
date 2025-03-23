package com.example.entity.repository.repors

import androidx.paging.PagingData
import com.example.core.module.Transaction
import kotlinx.coroutines.flow.Flow


interface ReportsRepository {
    fun getTransferHistory(size: Int, currentPage: Int): Flow<PagingData<Transaction>>
}