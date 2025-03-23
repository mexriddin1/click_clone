package com.example.usecase.usecase.auth

import com.example.core.module.auth.LoginData
import com.example.core.module.auth.RegisterData
import com.example.core.module.auth.VerifyData
import kotlinx.coroutines.flow.Flow


interface AuthUseCase {
    fun registerUser(body: RegisterData): Flow<Result<Unit>>
    fun registerVerifyCode(body: VerifyData): Flow<Result<Unit>>

    fun loginUser(body: LoginData): Flow<Result<Unit>>
    fun loginVerifyCode(body: VerifyData): Flow<Result<Unit>>

    fun resendCode(): Flow<Result<Unit>>
}