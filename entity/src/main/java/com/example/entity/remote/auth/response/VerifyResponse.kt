package com.example.entity.remote.auth.response

import com.google.gson.annotations.SerializedName

data class VerifyResponse(
    @SerializedName("access-token")
    val access: String,
    @SerializedName("refresh-token")
    val refresh: String
)