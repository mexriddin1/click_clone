package com.example.entity.repository.card

import com.example.entity.remote.card.request.NewCardRequest
import com.example.entity.remote.card.response.CardResponse
import kotlinx.coroutines.flow.Flow


interface CardRepository {
    suspend fun getAll(): Flow<Result<List<CardResponse>>>
    suspend fun add(card: NewCardRequest): Result<Unit>
}