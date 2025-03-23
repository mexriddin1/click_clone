package com.example.usecase.di

import com.example.usecase.usecase.card.CardUseCase
import com.example.usecase.usecase.card.CardUseCaseImpl
import com.example.usecase.usecase.repors.ReportsUseCase
import com.example.usecase.usecase.repors.ReportsUseCaseImpl
import com.example.usecase.usecase.transfers.TransferUseCase
import com.example.usecase.usecase.transfers.TransferUseCaseImpl
import com.example.usecase.usecase.auth.AuthUseCase
import com.example.usecase.usecase.auth.AuthUseCaseImpl
import com.example.usecase.usecase.screen_manager.ScreenManagerUserCase
import com.example.usecase.usecase.screen_manager.ScreenManagerUserCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {
    @Binds
    fun bindAuthUseCase(impl: AuthUseCaseImpl): AuthUseCase

    @Binds
    fun bindCardUseCase(impl: CardUseCaseImpl): CardUseCase

    @Binds
    fun bindTransferUseCase(impl: TransferUseCaseImpl): TransferUseCase

    @Binds
    fun bindReportsUseCase(impl: ReportsUseCaseImpl): ReportsUseCase

    @Binds
    fun bindScreenManagerUserCase(impl: ScreenManagerUserCaseImpl): ScreenManagerUserCase
}