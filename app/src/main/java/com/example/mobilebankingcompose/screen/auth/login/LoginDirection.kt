package com.example.mobilebankingcompose.screen.auth.login

import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.mobilebankingcompose.screen.auth.register.RegisterScreen
import com.example.mobilebankingcompose.screen.auth.verify_code.VerifyCodeScreen
import com.example.preseneter.auth.LoginContract
import javax.inject.Inject

class LoginDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : LoginContract.Direction {

    override suspend fun openRegister() {
        appNavigator.navigateTo(RegisterScreen())
    }

    override suspend fun openVerify() {
        appNavigator.replace(VerifyCodeScreen())
    }
}
