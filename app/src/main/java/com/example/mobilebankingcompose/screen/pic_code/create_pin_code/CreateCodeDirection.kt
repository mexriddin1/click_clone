package com.example.mobilebankingcompose.screen.pic_code.create_pin_code

import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.mobilebankingcompose.screen.auth.login.LoginScreen
import com.example.mobilebankingcompose.screen.pic_code.check_pin_code.EnterCodeScreen
import com.example.preseneter.pin_code.CreateCodeContract
import javax.inject.Inject

class CreateCodeDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : CreateCodeContract.Direction {

    override suspend fun openEnterScreen() {
        appNavigator.replace(EnterCodeScreen())
    }

    override suspend fun openLoginScreen() {
        appNavigator.replace(LoginScreen())
    }

}
