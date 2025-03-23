package com.example.core.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

fun <T> result(block: suspend () -> T): Flow<Result<T>> = flow {
    try {
        emit(Result.success(block.invoke()))
    } catch (e: Exception) {
        emit(Result.failure(e))
    }
}.catch { e ->
    emit(Result.failure(Exception(e)))
}






