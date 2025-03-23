package com.example.entity.remote.repors.respons

import com.example.core.module.Transaction
import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("total-elements") val totalElements: Int,
    @SerializedName("total-pages") val totalPages: Int,
    @SerializedName("current-page") val page: Int,
    @SerializedName("child") val child: List<Transaction>
)
