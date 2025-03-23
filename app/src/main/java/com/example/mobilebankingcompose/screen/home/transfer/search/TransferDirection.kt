package com.example.mobilebankingcompose.screen.home.transfer.search

import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.mobilebankingcompose.screen.home.transfer.user.UserScreen
import com.example.preseneter.transfer.TransferContract
import javax.inject.Inject

class TransferDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : TransferContract.Direction {

    override suspend fun openUserScreen(user: com.example.core.module.transfer.TransferUser) {
        appNavigator.navigateTo(UserScreen(user))
    }
}
