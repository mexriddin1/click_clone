package com.example.mobilebankingcompose.screen.auth.login.Componest

fun checkLogin(phone: String, password: String): Boolean {
    return (phone.length == 13 && phone.startsWith("+998")) && password.length >= 6
}