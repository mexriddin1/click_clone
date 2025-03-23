package com.example.entity.remote.auth.request

import com.google.gson.annotations.SerializedName

data class VerifyRequest(
    var token:String = "",
    val code: String
)

data class RefreshTokenRequest(
    @SerializedName("refresh-token")
    val refreshToken: String
)
