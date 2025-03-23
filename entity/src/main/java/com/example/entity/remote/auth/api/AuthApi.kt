package com.example.entity.remote.auth.api

import com.example.entity.remote.auth.request.LoginRequest
import com.example.entity.remote.auth.request.UpdateToken
import com.example.entity.remote.auth.response.UpdateTokenResponse
import com.example.entity.remote.auth.response.VerifyResponse
import com.example.entity.remote.auth.request.RegisterRequest
import com.example.entity.remote.auth.request.VerifyRequest
import com.example.entity.remote.auth.response.LoginResponse
import com.example.entity.remote.auth.response.RegisterResponse
import com.example.entity.remote.auth.response.ResendCodeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

internal interface AuthApi {

    @POST("v1/auth/sign-up")
    suspend fun registerUser(@Body body: RegisterRequest): Response<RegisterResponse>

    @POST("v1/auth/sign-up/verify")
    suspend fun registerVerifyCode(
        @Body body: VerifyRequest
    ): Response<VerifyResponse>

    @POST("v1/auth/sign-in/verify")
    suspend fun loginVerifyCode(
        @Body body: VerifyRequest
    ): Response<VerifyResponse>

    @POST("v1/auth/sign-in")
    suspend fun loginUser(@Body body: LoginRequest): Response<LoginResponse>

    @POST("v1/auth/update-token")
    suspend fun updateToken(@Body body: UpdateToken): Response<UpdateTokenResponse>

    @POST("v1/auth/sign-in/resend")
    suspend fun resendCode(@Header("token") token: String): Response<ResendCodeResponse>
}