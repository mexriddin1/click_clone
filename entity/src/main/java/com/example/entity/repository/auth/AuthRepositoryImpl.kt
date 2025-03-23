package com.example.entity.repository.auth


import com.example.core.module.main.TokenType
import com.example.entity.local.AppLocalStorage
import com.example.entity.remote.auth.api.AuthApi
import com.example.entity.remote.auth.request.LoginRequest
import com.example.entity.remote.auth.request.RegisterRequest
import com.example.entity.remote.auth.request.VerifyRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val localStorage: AppLocalStorage
) : AuthRepository {

    override suspend fun registerUser(body: RegisterRequest): Result<Unit> =
        withContext(Dispatchers.IO) {
            val result = api.registerUser(body)

            when {
                result.isSuccessful -> {
                    saveToken(result.body()!!.token, TokenType.TOKEN)
                    Result.success(Unit)
                }

                else -> {
                    Result.failure(Throwable(result.errorBody()!!.string()))
                }
            }

        }

    override suspend fun registerVerifyCode(body: VerifyRequest): Result<Unit> =
        withContext(Dispatchers.IO) {
            val result =
                api.registerVerifyCode(body.copy(token = getToken(TokenType.TOKEN)))

            if (result.isSuccessful && result.body() != null) {
                saveToken(
                    result.body()!!.access,
                    TokenType.ACCESS_TOKEN
                )
                saveToken(
                    result.body()!!.refresh,
                    TokenType.REFRESH_TOKEN
                )
                Result.success(Unit)
            } else Result.failure(Throwable(result.errorBody()!!.string()))

        }

    override suspend fun loginVerifyCode(body: VerifyRequest): Result<Unit> =
        withContext(Dispatchers.IO) {
            val result =
                api.loginVerifyCode(body.copy(token = getToken(TokenType.TOKEN)))

            if (result.isSuccessful && result.body() != null) {
                saveToken(
                    result.body()!!.access,
                    TokenType.ACCESS_TOKEN
                )
                saveToken(
                    result.body()!!.refresh,
                    TokenType.REFRESH_TOKEN
                )
                Result.success(Unit)
            } else Result.failure(Throwable(result.errorBody()!!.string()))

        }

    override suspend fun loginUser(body: LoginRequest): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.loginUser(body)

        if (result.isSuccessful && result.body() != null) {
            saveToken(result.body()!!.token, TokenType.TOKEN)
            Result.success(Unit)
        } else Result.failure(Throwable(result.errorBody()!!.string()))
    }

    override suspend fun resendCode(): Result<Unit> = withContext(Dispatchers.IO) {
        val result = api.resendCode(getToken(TokenType.TOKEN))
        if (result.isSuccessful && result.body() != null) {
            Result.success(Unit)
        } else Result.failure(Throwable(result.errorBody()!!.string()))

    }

    private fun saveToken(token: String, type: TokenType) {
        when (type) {
            TokenType.TOKEN -> localStorage.token = token
            TokenType.ACCESS_TOKEN -> localStorage.accessToken = token
            else -> localStorage.refreshToken = token
        }
    }

    private fun getToken(type: TokenType): String {
        return when (type) {
            TokenType.TOKEN -> localStorage.token
            else -> localStorage.refreshToken
        }
    }
}