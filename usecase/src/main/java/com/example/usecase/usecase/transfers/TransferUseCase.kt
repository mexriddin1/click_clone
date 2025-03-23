package com.example.usecase.usecase.transfers

import com.example.entity.remote.transfer.request.GetPanRequest
import com.example.entity.remote.transfer.request.TransactionRequest
import com.example.entity.remote.transfer.response.GetPanResponse
import kotlinx.coroutines.flow.Flow

interface TransferUseCase {
    fun getPan(pan: GetPanRequest): Flow<Result<GetPanResponse>>
    fun transfer(data: TransactionRequest): Flow<Result<Unit>>
    fun verifyTransfer(code: String): Flow<Result<Unit>>
}