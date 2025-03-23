package com.example.mobilebankingcompose.screen.home.main

import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.mobilebankingcompose.screen.card.card_screen.CardScreen
import com.example.preseneter.home.MainContract
import javax.inject.Inject

class MainDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : MainContract.Direction {
    override suspend fun openCard() {
        appNavigator.navigateTo(CardScreen())
    }

}
