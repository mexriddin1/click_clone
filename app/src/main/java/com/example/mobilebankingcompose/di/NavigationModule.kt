package com.example.mobilebankingcompose.di

import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.mobilebankingcompose.core.utils.navigation.AppNavigatorDispatcher
import com.example.mobilebankingcompose.core.utils.navigation.AppNavigatorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindAppNavigationHandler(impl: AppNavigatorDispatcher): AppNavigatorHandler

    @Binds
    fun bindAppNavigation(impl: AppNavigatorDispatcher): AppNavigator
}