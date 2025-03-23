package com.example.mobilebankingcompose.screen.auth.register.constant

import com.example.core.module.auth.RegisterData

fun checkRegister(user: RegisterData, checkPassword: String) =
    user.lastName.length >= 3 &&
            user.firstName.length >= 3 &&
            user.birthDate.isNotBlank() &&
            user.phone.length == 13 &&
            user.phone.startsWith("+998") &&
            user.password.length >= 6 && user.password == checkPassword

