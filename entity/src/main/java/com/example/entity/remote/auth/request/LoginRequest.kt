package com.example.entity.remote.auth.request

data class LoginRequest(
    val phone: String,
    val password: String
)