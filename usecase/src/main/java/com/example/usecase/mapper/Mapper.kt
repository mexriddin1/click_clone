package com.example.usecase.mapper


import com.example.core.module.auth.LoginData
import com.example.core.module.auth.RegisterData
import com.example.core.module.auth.VerifyData
import com.example.core.module.card.CardDate
import com.example.core.module.card.NewCardDate
import com.example.entity.remote.auth.request.LoginRequest
import com.example.entity.remote.card.request.NewCardRequest
import com.example.entity.remote.card.response.CardResponse
import com.example.entity.remote.auth.request.RegisterRequest
import com.example.entity.remote.auth.request.VerifyRequest

internal fun RegisterData.toRequest() = RegisterRequest(
    phone = this.phone,
    password = this.password,
    firstName = this.firstName,
    lastName = this.lastName,
    birthDate = this.birthDate,
    gender = this.gender
)

internal fun VerifyData.toRequest() = VerifyRequest(
    code = this.code
)

internal fun LoginData.toRequest() = LoginRequest(
    phone = this.phone, password = this.password
)

internal fun NewCardDate.toRequest() = NewCardRequest(
    pan = this.pan,
    expiredYear = this.expiredYear,
    expiredMonth = this.expiredMonth,
    name = this.name
)

internal fun CardResponse.toData() = CardDate(
    id = this.id,
    name = this.name,
    amount = this.amount,
    owner = this.owner,
    pan = this.pan,
    expiredYear = this.expiredYear,
    expiredMonth = this.expiredMonth,
    themeType = this.themeType,
    isVisible = this.isVisible,
    isMainCard = false
)