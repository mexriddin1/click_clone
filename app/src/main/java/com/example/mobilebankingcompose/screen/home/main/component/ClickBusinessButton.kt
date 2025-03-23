package com.example.mobilebankingcompose.screen.home.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobilebankingcompose.R

@Composable
fun BoxScope.ClickBusinessButton(
    click: () -> Unit = {}
) {
    Image(
        painter = painterResource(R.drawable.click_business_button),
        contentDescription = null,
        modifier = Modifier
            .padding(top = 18.dp)
            .size(150.dp).align(Alignment.TopStart),
    )
}