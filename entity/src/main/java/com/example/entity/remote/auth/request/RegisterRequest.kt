package com.example.entity.remote.auth.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    val phone: String,

    val password: String,

    @SerializedName("first-name")
    val firstName: String,

    @SerializedName("last-name")
    val lastName: String,

    @SerializedName("born-date")
    val birthDate: String,

    val gender: String
)