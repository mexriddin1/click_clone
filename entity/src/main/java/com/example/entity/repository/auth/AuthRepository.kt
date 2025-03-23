package com.example.entity.repository.auth

import com.example.entity.remote.auth.request.LoginRequest
import com.example.entity.remote.auth.request.RegisterRequest
import com.example.entity.remote.auth.request.VerifyRequest


interface AuthRepository {
    suspend fun registerUser(body: RegisterRequest): Result<Unit>
    suspend fun registerVerifyCode(body: VerifyRequest): Result<Unit>

    suspend fun loginUser(body: LoginRequest): Result<Unit>
    suspend fun loginVerifyCode(body: VerifyRequest): Result<Unit>

    suspend fun resendCode(): Result<Unit>
}
