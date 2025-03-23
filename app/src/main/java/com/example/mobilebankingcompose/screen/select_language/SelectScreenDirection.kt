package com.example.mobilebankingcompose.screen.select_language

import com.example.mobilebankingcompose.screen.auth.login.LoginScreen
import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.preseneter.select_language.SelectLanguageContract
import javax.inject.Inject

class SelectScreenDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : SelectLanguageContract.Direction {

    override suspend fun openLogin() {
        appNavigator.replace(LoginScreen())
    }
}