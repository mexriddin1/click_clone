package com.example.mobilebankingcompose.screen.auth.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobilebankingcompose.R

@Composable
fun LoadIconBar(click: () -> Unit) {
    Image(painter = painterResource(
        when (com.example.entity.local.LocationHelper.getLocation()) {
            com.example.core.module.main.Language.UZB -> R.drawable.uz_flag_oval
            com.example.core.module.main.Language.RUS -> R.drawable.ru_flag_oval
            else -> R.drawable.en_flag_oval
        }
    ),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .padding(start = 15.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                click.invoke()
            })
}