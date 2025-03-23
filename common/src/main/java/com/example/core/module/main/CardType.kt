package com.example.core.module.main

import androidx.annotation.DrawableRes
import com.example.core.R


enum class CardType(@DrawableRes val id: Int, @DrawableRes val logo: Int) {
    KAPITAL(R.drawable.kapitalbank, R.drawable.kapital_logo),
    UZCARD(R.drawable.uzcard, R.drawable.uzcard_logo),
    CLICK(R.drawable.click_kas, R.drawable.click_icon),
    HUMO(R.drawable.humo, R.drawable.humo_logo),
    NBU(R.drawable.nbu_bank, R.drawable.nbu_logo)
}

