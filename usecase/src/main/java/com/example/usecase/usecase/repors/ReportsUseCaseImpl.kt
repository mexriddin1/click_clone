package com.example.usecase.usecase.repors

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.module.Transaction
import com.example.entity.remote.repors.api.ReportsApi
import com.example.entity.repository.repors.PaginationSource
import com.example.entity.repository.repors.ReportsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


internal class ReportsUseCaseImpl @Inject constructor(
    private val api: ReportsApi
) : ReportsUseCase {
    override fun getTransferHistory(size: Int, currentPage: Int): Flow<PagingData<Transaction>> =
        Pager(
            config = PagingConfig(size),
            pagingSourceFactory = {
                PaginationSource(
                    api = api
                )
            },
        ).flow
}