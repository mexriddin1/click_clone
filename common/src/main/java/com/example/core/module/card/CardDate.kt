package com.example.core.module.card

data class CardDate(
    val id: Int,
    val name: String,
    val amount: Long,
    val owner: String,
    val pan: String,
    val expiredYear: Int,
    val expiredMonth: Int,
    val themeType: Int,
    val isVisible: Boolean,
    val isMainCard:Boolean = false
)