package com.example.mobilebankingcompose.screen.auth.verify_code

import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.mobilebankingcompose.screen.pic_code.create_pin_code.CreateCodeScreen
import com.example.preseneter.auth.VerifyCodeContract
import javax.inject.Inject

class VerifyCodeDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : VerifyCodeContract.Direction {

    override suspend fun openCreateCode() {
        appNavigator.replace(CreateCodeScreen())
    }
}
