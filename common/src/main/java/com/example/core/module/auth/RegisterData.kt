package com.example.core.module.auth

data class RegisterData(
    val phone: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val gender: String
)