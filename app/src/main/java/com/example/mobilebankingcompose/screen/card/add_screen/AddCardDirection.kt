package com.example.mobilebankingcompose.screen.card.add_screen

import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.preseneter.card.AddCardContract
import javax.inject.Inject

class AddCardDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : AddCardContract.Direction {
    override suspend fun back() {
        appNavigator.back()
    }

}
