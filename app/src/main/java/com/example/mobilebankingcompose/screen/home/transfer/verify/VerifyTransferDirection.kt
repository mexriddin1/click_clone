package com.example.mobilebankingcompose.screen.home.transfer.verify

import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.preseneter.transfer.VerifyTransferContract
import javax.inject.Inject

class VerifyTransferDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : VerifyTransferContract.Direction {
    override suspend fun back() {
        appNavigator.back()
    }

}
