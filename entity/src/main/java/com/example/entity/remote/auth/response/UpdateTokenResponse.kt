package com.example.entity.remote.auth.response

import com.google.gson.annotations.SerializedName

data class UpdateTokenResponse(
    @SerializedName("access-token")
    val accessToken: String,

    @SerializedName("refresh-token")
    val refreshToken: String
)