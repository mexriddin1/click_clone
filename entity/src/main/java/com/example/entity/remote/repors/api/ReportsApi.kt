package com.example.entity.remote.repors.api

import com.example.entity.Cacheable
import com.example.entity.remote.repors.respons.TransactionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReportsApi {

    @Cacheable
    @GET("v1/transfer/history")
    suspend fun getTransactions(
        @Query("size") size: Int,
        @Query("current-page") currentPage: Int
    ): Response<TransactionResponse>

}