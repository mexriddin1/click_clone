package com.example.mobilebankingcompose.screen.card.card_screen

import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.mobilebankingcompose.screen.card.add_screen.AddScreen
import com.example.preseneter.card.CardContract
import javax.inject.Inject

class CardDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : CardContract.Direction {
    override suspend fun openAddCard() {
        appNavigator.navigateTo(AddScreen())
    }

    override suspend fun back() {
        appNavigator.back()
    }

}
