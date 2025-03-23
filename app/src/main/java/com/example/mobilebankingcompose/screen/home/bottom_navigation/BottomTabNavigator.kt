package com.example.mobilebankingcompose.screen.home.bottom_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.ComponentColor
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.mobilebankingcompose.core.ui.theme.TextGray
import com.example.mobilebankingcompose.screen.home.main.MainScreen
import com.example.mobilebankingcompose.screen.home.payment.PaymentScreen
import com.example.mobilebankingcompose.screen.home.reports.ReportsScreen
import com.example.mobilebankingcompose.screen.home.transfer.search.TransferScreen

class BottomTabNavigator : Screen {
    @Composable
    override fun Content() {
        TabNavigator(MainScreen()) {
            Scaffold(
                containerColor = DarkBackground,
                bottomBar = {
                NavigationBar(
                    containerColor = ComponentColor, modifier = Modifier.height(83.dp)
                ) {
                    TabNavigatorItem(MainScreen())
                    TabNavigatorItem(PaymentScreen())
                    TabNavigatorItem(TransferScreen())
                    TabNavigatorItem(ReportsScreen())
//                    TabNavigatorItem(ReportsScreen())
                }
            }, content = { paddingValues ->
                Box(
                    Modifier
                        .background(color = DarkBackground)
                        .padding(bottom = paddingValues.calculateBottomPadding())
                ) {
                    CurrentTab()
                }
            })
        }
    }
}

@Composable
fun RowScope.TabNavigatorItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val interactionSource = remember { MutableInteractionSource() }
    val isSelected = rememberUpdatedState(tabNavigator.current == tab)
    NavigationBarItem(selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = tab.options.title,
                    tint = if (isSelected.value) Cerulean else TextGray,
                    modifier = Modifier.size(25.dp)
                )
            }
        },
        label = {
            Text(
                text = tab.options.title, style = TextStyle(
                    fontSize = 13.sp, fontFamily = FontFamily(Font(R.font.euclid_circular_regular))
                )
            )
        },

        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent,
            selectedIconColor = Cerulean,
            unselectedIconColor = TextGray,
            selectedTextColor = Cerulean,
            unselectedTextColor = TextGray,
            disabledIconColor = Color.Transparent
        ),
        modifier = Modifier
            .background(color = Color.Transparent)
            .height(40.dp)
            .clickable(indication = null, interactionSource = interactionSource) {})

}