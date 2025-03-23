package com.example.core.module

data class Transaction(
    val type: String,
    val from: String,
    val to: String,
    val amount: Int,
    val time: Long
)