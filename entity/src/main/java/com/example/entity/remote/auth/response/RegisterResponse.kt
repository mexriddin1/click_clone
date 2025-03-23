package com.example.entity.remote.auth.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("token")
    val token: String,
)