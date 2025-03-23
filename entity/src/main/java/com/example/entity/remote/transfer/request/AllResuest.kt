package com.example.entity.remote.transfer.request

import com.google.gson.annotations.SerializedName

data class GetPanRequest(
    val pan: String
)

data class TransactionRequest(
    @SerializedName("type") val type: String,
    @SerializedName("sender-id") val senderId: String,
    @SerializedName("receiver-pan") val receiverPan: String,
    @SerializedName("amount") val amount: Int
)

data class VerifyTransferCodeRequest(
    val token: String = "",
    val code: String
)

