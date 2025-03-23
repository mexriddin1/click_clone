package com.example.entity.remote.card.api

import com.example.entity.Cacheable
import com.example.entity.remote.card.request.NewCardRequest
import com.example.entity.remote.card.response.CardMessageResponse
import com.example.entity.remote.card.response.CardResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CardApi {

    @Cacheable
    @GET("v1/card")
    suspend fun getAllCard(): Response<List<CardResponse>>

    @POST("v1/card")
    suspend fun addNewCard(@Body card: NewCardRequest): Response<CardMessageResponse>

}