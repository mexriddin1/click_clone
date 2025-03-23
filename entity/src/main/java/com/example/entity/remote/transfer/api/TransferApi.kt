package com.example.entity.remote.transfer.api

import com.example.entity.remote.transfer.request.GetPanRequest
import com.example.entity.remote.transfer.request.TransactionRequest
import com.example.entity.remote.transfer.request.VerifyTransferCodeRequest
import com.example.entity.remote.transfer.response.GetPanResponse
import com.example.entity.remote.transfer.response.TransferTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TransferApi {

    @POST("v1/transfer/card-owner")
    suspend fun getPan(@Body pan: GetPanRequest): Response<GetPanResponse>

    @POST("v1/transfer/transfer")
    suspend fun transfer(@Body data: TransactionRequest): Response<TransferTokenResponse>

    @POST("v1/transfer/transfer/verify")
    suspend fun verifyTransfer(@Body data: VerifyTransferCodeRequest): Response<Unit>
}