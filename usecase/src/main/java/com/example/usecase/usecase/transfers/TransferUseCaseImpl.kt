package com.example.usecase.usecase.transfers

import com.example.core.extension.result
import com.example.entity.remote.transfer.request.GetPanRequest
import com.example.entity.remote.transfer.request.TransactionRequest
import com.example.entity.remote.transfer.response.GetPanResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


internal class TransferUseCaseImpl @Inject constructor(
    private val repository: com.example.entity.repository.transfers.TransferRepository
) : TransferUseCase {

    override fun getPan(pan: GetPanRequest): Flow<Result<GetPanResponse>> = flow {
        repository.getPan(pan).onSuccess {
            emit(Result.success(it))
        }
    }.catch {
        emit(Result.failure(it))
    }

    override fun transfer(data: TransactionRequest): Flow<Result<Unit>> =
        result {
            repository.transfer(data)
        }

    override fun verifyTransfer(code: String): Flow<Result<Unit>> =
        result {
            repository.verifyTransfer(code)
        }

}