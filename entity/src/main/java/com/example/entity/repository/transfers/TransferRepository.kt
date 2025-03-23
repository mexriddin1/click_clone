package com.example.entity.repository.transfers

import com.example.entity.remote.transfer.request.GetPanRequest
import com.example.entity.remote.transfer.request.TransactionRequest
import com.example.entity.remote.transfer.response.GetPanResponse


interface TransferRepository {
    suspend fun getPan(pan: GetPanRequest): Result<GetPanResponse>
    suspend fun transfer(data: TransactionRequest): Result<Unit>
    suspend fun verifyTransfer(code:String): Result<Unit>
}