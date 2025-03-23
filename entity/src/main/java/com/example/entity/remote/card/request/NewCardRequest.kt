package com.example.entity.remote.card.request

import com.google.gson.annotations.SerializedName

data class NewCardRequest(
    val pan: String,
    @SerializedName("expired-year") val expiredYear: String,
    @SerializedName("expired-month") val expiredMonth: String,
    val name: String
)
