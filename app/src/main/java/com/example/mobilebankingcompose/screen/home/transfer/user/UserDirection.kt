package com.example.mobilebankingcompose.screen.home.transfer.user

import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.mobilebankingcompose.screen.home.transfer.verify.VerifyCodeScreen
import com.example.preseneter.transfer.UserContract
import javax.inject.Inject

class UserDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : UserContract.Direction {
    override suspend fun verifyScreen() {
        appNavigator.navigateTo(VerifyCodeScreen())
    }

    override suspend fun back() {
        appNavigator.back()
    }

}
