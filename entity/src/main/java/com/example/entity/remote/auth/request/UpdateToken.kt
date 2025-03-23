package com.example.entity.remote.auth.request

import com.google.gson.annotations.SerializedName

data class UpdateToken(
    @SerializedName("refresh-token")
    val refreshToken: String
)
