package com.example.entity.repository.transfers

import com.example.entity.local.AppLocalStorage
import com.example.entity.remote.transfer.api.TransferApi
import com.example.entity.remote.transfer.request.GetPanRequest
import com.example.entity.remote.transfer.request.TransactionRequest
import com.example.entity.remote.transfer.request.VerifyTransferCodeRequest
import com.example.entity.remote.transfer.response.GetPanResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


internal class TransferRepositoryImpl @Inject constructor(
    val api: TransferApi,
    private val appLocalStorage: AppLocalStorage
) : TransferRepository {
    override suspend fun getPan(pan: GetPanRequest): Result<GetPanResponse> =
        withContext(Dispatchers.IO) {
            val request = api.getPan(pan)
            if (request.isSuccessful && request.body() != null) {
                Result.success(request.body()!!)
            } else Result.failure(Throwable(request.errorBody()!!.string()))
        }

    override suspend fun transfer(data: TransactionRequest): Result<Unit> =
        withContext(Dispatchers.IO) {
            val request = api.transfer(data)
            if (request.isSuccessful && request.body() != null) {
                appLocalStorage.token = request.body()!!.token
                Result.success(Unit)
            } else Result.failure(Throwable(request.errorBody()!!.string()))
        }

    override suspend fun verifyTransfer(code: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            val request = api.verifyTransfer(
                VerifyTransferCodeRequest(
                    appLocalStorage.token,
                    code
                )
            )
            if (request.isSuccessful) {
                Result.success(Unit)
            } else Result.failure(Throwable(request.errorBody()!!.string()))
        }
}