package com.example.mobilebankingcompose.di

import com.example.preseneter.auth.LoginContract
import com.example.mobilebankingcompose.screen.auth.login.LoginDirection
import com.example.preseneter.auth.RegisterContract
import com.example.mobilebankingcompose.screen.auth.register.RegisterDirection
import com.example.preseneter.auth.VerifyCodeContract
import com.example.mobilebankingcompose.screen.auth.verify_code.VerifyCodeDirection
import com.example.preseneter.card.AddCardContract
import com.example.mobilebankingcompose.screen.card.add_screen.AddCardDirection
import com.example.preseneter.card.CardContract
import com.example.mobilebankingcompose.screen.card.card_screen.CardDirection
import com.example.preseneter.home.MainContract
import com.example.mobilebankingcompose.screen.home.main.MainDirection
import com.example.preseneter.home.ReportsContract
import com.example.mobilebankingcompose.screen.home.reports.ReportsDirection
import com.example.preseneter.transfer.TransferContract
import com.example.mobilebankingcompose.screen.home.transfer.search.TransferDirection
import com.example.preseneter.transfer.UserContract
import com.example.mobilebankingcompose.screen.home.transfer.user.UserDirection
import com.example.preseneter.transfer.VerifyTransferContract
import com.example.mobilebankingcompose.screen.home.transfer.verify.VerifyTransferDirection
import com.example.preseneter.pin_code.EnterCodeContract
import com.example.mobilebankingcompose.screen.pic_code.check_pin_code.EnterCodeDirection
import com.example.preseneter.pin_code.CreateCodeContract
import com.example.mobilebankingcompose.screen.pic_code.create_pin_code.CreateCodeDirection
import com.example.preseneter.select_language.SelectLanguageContract
import com.example.mobilebankingcompose.screen.select_language.SelectScreenDirection
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DirectionModule {

    @Binds
    fun bindSelectDirection(impl: SelectScreenDirection): SelectLanguageContract.Direction

    @Binds
    fun bindLoginDirection(impl: LoginDirection): LoginContract.Direction

    @Binds
    fun bindRegisterDirection(impl: RegisterDirection): RegisterContract.Direction

    @Binds
    fun bindVerifyCodeDirection(impl: VerifyCodeDirection): VerifyCodeContract.Direction

    @Binds
    fun bindCreateCodeDirection(impl: CreateCodeDirection): CreateCodeContract.Direction

    @Binds
    fun bindEnterCodeDirection(impl: EnterCodeDirection): EnterCodeContract.Direction

    @Binds
    fun bindMainDirection(impl: MainDirection): MainContract.Direction

    @Binds
    fun bindCardDirection(impl: CardDirection): CardContract.Direction

    @Binds
    fun bindAddCardDirection(impl: AddCardDirection): AddCardContract.Direction

    @Binds
    fun bindUserDirection(impl: UserDirection): UserContract.Direction


    @Binds
    fun bindTransferDirection(impl: TransferDirection): TransferContract.Direction

    @Binds
    fun bindVerifyTransferDirection(impl: VerifyTransferDirection): VerifyTransferContract.Direction

    @Binds
    fun bindReportsDirection(impl: ReportsDirection): ReportsContract.Direction
}