package com.example.entity.remote.card.response

import com.google.gson.annotations.SerializedName

data class CardResponse(
    val id: Int,
    val name: String,
    val amount: Long,
    val owner: String,
    val pan: String,
    @SerializedName("expired-year") val expiredYear: Int,
    @SerializedName("expired-month") val expiredMonth: Int,
    @SerializedName("theme-type") val themeType: Int,
    @SerializedName("is-visible") val isVisible: Boolean
)
