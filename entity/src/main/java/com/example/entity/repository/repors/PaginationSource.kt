package com.example.entity.repository.repors

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.module.Transaction
import com.example.entity.remote.repors.api.ReportsApi


class PaginationSource(private val api: ReportsApi) : PagingSource<Int, Transaction>() {

    override fun getRefreshKey(state: PagingState<Int, Transaction>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?: state.closestPageToPosition(
                anchor
            )?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Transaction> {
        val page = params.key ?: 1
        return try {
            val apiResponse = api.getTransactions(50, page)
            val apiResponse1 = apiResponse.body()!!.child
            LoadResult.Page(
                data = apiResponse1,
                nextKey = if (apiResponse.body()!!.page > page) page.plus(1) else null,
                prevKey = if (page > 1) page.minus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}