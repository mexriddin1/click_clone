package com.example.entity.repository.card

import com.example.entity.remote.card.api.CardApi
import com.example.entity.remote.card.request.NewCardRequest
import com.example.entity.remote.card.response.CardResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject


internal class CardRepositoryImpl @Inject constructor(
    private val api: CardApi
) : CardRepository {

    override suspend fun getAll(): Flow<Result<List<CardResponse>>> = flow {
        val request = api.getAllCard()
        if (request.isSuccessful && request.body() != null) {
            emit(Result.success(request.body()!!))
        }
    }.flowOn(Dispatchers.IO).catch {
        emit(Result.failure(Throwable(it.message.toString())))
    }

    override suspend fun add(card: NewCardRequest): Result<Unit> =
        withContext(Dispatchers.IO) {
            val request = api.addNewCard(card)

            if (request.isSuccessful && request.body() != null) {
                Result.success(Unit)
            } else Result.failure(Throwable(request.errorBody()!!.string()))
        }
}