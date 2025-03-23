package com.example.mobilebankingcompose.screen.pic_code.check_pin_code

import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.mobilebankingcompose.screen.auth.login.LoginScreen
import com.example.mobilebankingcompose.screen.home.bottom_navigation.BottomTabNavigator
import com.example.mobilebankingcompose.screen.pic_code.create_pin_code.CreateCodeScreen
import com.example.preseneter.pin_code.EnterCodeContract
import javax.inject.Inject

class EnterCodeDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : EnterCodeContract.Direction {
    override suspend fun openLoginScreen() {
        appNavigator.replace(LoginScreen())
    }

    override suspend fun openMainScreen() {
        appNavigator.replace(BottomTabNavigator())
    }

    override suspend fun openCreateScreen() {
        appNavigator.replace(CreateCodeScreen())
    }

}
