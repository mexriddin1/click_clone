package com.example.entity.repository.repors

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.module.Transaction
import com.example.entity.remote.repors.api.ReportsApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


internal class ReportsRepositoryImpl @Inject constructor(
    private val api: ReportsApi
) : ReportsRepository {
    override fun getTransferHistory(size: Int, currentPage: Int): Flow<PagingData<Transaction>> =
        Pager(
            config = PagingConfig(size), pagingSourceFactory = { PaginationSource(api = api) },
        ).flow
}