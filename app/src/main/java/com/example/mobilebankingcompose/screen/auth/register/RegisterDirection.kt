package com.example.mobilebankingcompose.screen.auth.register

import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.mobilebankingcompose.screen.home.transfer.verify.VerifyCodeScreen
import com.example.preseneter.auth.RegisterContract
import javax.inject.Inject

class RegisterDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : RegisterContract.Direction {

    override suspend fun openVerify(){
        appNavigator.navigateTo(VerifyCodeScreen())
    }
}

