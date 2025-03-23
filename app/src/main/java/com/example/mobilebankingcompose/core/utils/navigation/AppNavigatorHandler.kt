package com.example.mobilebankingcompose.core.utils.navigation

import kotlinx.coroutines.flow.Flow

interface AppNavigatorHandler {
    val navigation: Flow<NavigatorArgs>
}