package com.example.usecase.usecase.auth

import com.example.core.extension.result
import com.example.core.module.auth.LoginData
import com.example.core.module.auth.RegisterData
import com.example.core.module.auth.VerifyData
import com.example.entity.repository.auth.AuthRepository
import com.example.usecase.mapper.toRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class AuthUseCaseImpl @Inject constructor(
    private val repository: AuthRepository,
) : AuthUseCase {
    override fun registerUser(body: RegisterData): Flow<Result<Unit>> =
        result {
            repository.registerUser(body.toRequest())
        }

    override fun registerVerifyCode(body: VerifyData): Flow<Result<Unit>> =
        result {
            repository.registerVerifyCode(body.toRequest())
        }

    override fun loginVerifyCode(body: VerifyData): Flow<Result<Unit>> =
        result {
            repository.loginVerifyCode(body.toRequest())
        }

    override fun loginUser(body: LoginData): Flow<Result<Unit>> =
        result {
            repository.loginUser(body.toRequest())
        }

    override fun resendCode(): Flow<Result<Unit>> = result {
        repository.resendCode()
    }

}
