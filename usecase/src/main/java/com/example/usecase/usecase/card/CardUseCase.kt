package com.example.usecase.usecase.card

import com.example.core.module.card.CardDate
import com.example.core.module.card.NewCardDate
import kotlinx.coroutines.flow.Flow

interface CardUseCase {
    fun getAllCard(): Flow<Result<List<CardDate>>>
    fun addNewCard(card: NewCardDate): Flow<Result<Unit>>
}