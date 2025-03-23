package com.example.entity.di

import com.example.entity.repository.auth.AuthRepository
import com.example.entity.repository.auth.AuthRepositoryImpl
import com.example.entity.repository.card.CardRepository
import com.example.entity.repository.card.CardRepositoryImpl
import com.example.entity.repository.repors.ReportsRepository
import com.example.entity.repository.repors.ReportsRepositoryImpl
import com.example.entity.repository.transfers.TransferRepository
import com.example.entity.repository.transfers.TransferRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindCardRepository(impl: CardRepositoryImpl): CardRepository

    @Binds
    fun bindTransferRepository(impl: TransferRepositoryImpl): TransferRepository

    @Binds
    fun bindReportsRepository(impl: ReportsRepositoryImpl): ReportsRepository
}