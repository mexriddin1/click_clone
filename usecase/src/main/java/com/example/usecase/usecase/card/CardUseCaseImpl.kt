package com.example.usecase.usecase.card

import com.example.core.module.card.CardDate
import com.example.core.module.card.NewCardDate
import com.example.usecase.mapper.toData
import com.example.usecase.mapper.toRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class CardUseCaseImpl @Inject constructor(
    private val repository: com.example.entity.repository.card.CardRepository
) : CardUseCase {

    override fun getAllCard(): Flow<Result<List<CardDate>>> = flow<Result<List<CardDate>>> {
        repository.getAll().collect {
            it.onSuccess {
                emit(Result.success(it.map { item ->
                    item.toData()
                        .copy(pan = "986008080809" + item.pan)
                }))
            }

            it.onFailure {
                emit(Result.failure(Throwable(it.message)))
            }
        }
    }.catch {
        emit(Result.failure(Throwable(it.message)))
    }


    override fun addNewCard(card: NewCardDate): Flow<Result<Unit>> =
        com.example.core.extension.result {
            repository.add(card.toRequest())
        }
}