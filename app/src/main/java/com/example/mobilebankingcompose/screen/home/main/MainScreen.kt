package com.example.mobilebankingcompose.screen.home.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.mobilebankingcompose.core.ui.theme.DarkBlueColor
import com.example.mobilebankingcompose.screen.home.main.component.CategoryButton
import com.example.mobilebankingcompose.screen.home.main.component.ClickBusinessButton
import com.example.mobilebankingcompose.screen.home.main.component.ContentTitleText
import com.example.mobilebankingcompose.screen.home.main.component.MainSumText
import com.example.mobilebankingcompose.screen.home.main.component.SearchTextField
import com.example.mobilebankingcompose.screen.home.main.component.SecondSumText
import com.example.preseneter.home.MainContract
import com.example.preseneter.home.MainViewModel
import kotlinx.coroutines.flow.filter

class MainScreen : Tab {
    override val options: TabOptions
        @Composable get() {
            val title = "Asosiy"
            val icon = painterResource(R.drawable.click_icon)

            return remember {
                TabOptions(
                    index = 0u, title = title, icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: MainContract.ViewModel = getViewModel<MainViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        MainScreenContent(uiState, viewModel::onAction)
    }

}

@Composable
fun MainScreenContent(
    uiState: State<MainContract.UiState> = remember { mutableStateOf(MainContract.UiState()) },
    onAction: (MainContract.Intent) -> Unit = {}
) {
    var eyeState by remember { mutableStateOf(true) }
    val listState = rememberLazyListState()
    var alpha by remember { mutableFloatStateOf(1f) }

    LaunchedEffect(listState) {
        Log.d("TTT", "scrolled")
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .filter {
                listState.firstVisibleItemIndex == 0
            }
            .collect { alpha = (1 - (it / 150f).coerceIn(0f, 1f)) }
    }

    Box(
        modifier = Modifier
            .background(DarkBackground)
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(Color.Transparent)
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.icon),
                    contentDescription = null,
                    modifier = Modifier.size(55.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                SearchTextField {}
            }
            Box(
                modifier = Modifier
            ) {
                Box(modifier = Modifier.alpha(alpha)) {
                    ClickBusinessButton {}
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .padding(start = 55.dp, end = 35.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Spacer(Modifier.height(35.dp))
                            ContentTitleText(stringResource(R.string.general_balance), 3.dp)
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MainSumText("57 950", eyeState)
                                Image(painter = painterResource(if (eyeState) R.drawable.sum_eye else R.drawable.sum_hide_eye),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(27.dp)
                                        .clickable(indication = null,
                                            interactionSource = remember { MutableInteractionSource() }) {

                                        }

                                )
                            }
                            Spacer(Modifier.height(12.dp))

                            Row {
                                Column {
                                    ContentTitleText(stringResource(R.string.my_wallet))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.wallet),
                                            contentDescription = null,
                                            tint = Cerulean,
                                            modifier = Modifier
                                                .size(40.dp)
                                                .padding(end = 10.dp),
                                        )
                                        SecondSumText(text = "50")
                                    }
                                }

                                Row {

                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(25.dp))
                }

                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .height(195.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (alpha == 1f) {
                                Box(
                                    modifier = Modifier
                                        .height(140.dp)
                                        .width(200.dp)
                                        .padding(start = 40.dp, top = 20.dp)
                                        .clickable(
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() }
                                        ) {
                                            onAction.invoke(MainContract.Intent.OpenCardScreen)
                                        })

                                Box(
                                    modifier = Modifier
                                        .height(55.dp)
                                        .width(50.dp)
                                        .padding(end = 20.dp, bottom = 30.dp)
                                        .clickable(
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() }
                                        ) {
                                            eyeState = !eyeState
                                        }
                                )
                            }
                        }
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                item {
                                    CategoryButton(id = R.drawable.barcode, text = "Click\nPass")
                                }
                                item {
                                    CategoryButton(id = R.drawable.mobile_nfc, text = "Click\nBoom")
                                }
                                item {
                                    CategoryButton(
                                        id = R.drawable.wallet_category,
                                        text = "Kartalar\nva hamyon"
                                    )
                                }
                                item {
                                    CategoryButton(id = R.drawable.qr_code, text = "Qr\nscanner")
                                }
                            }
                        }
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 25.dp)
                                .height(100.dp)
                                .fillMaxWidth()
                        ) {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier
                            ) {
                                items(2) {
                                    Surface(
                                        modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp - 32.dp),
                                        shape = RoundedCornerShape(14.dp),
                                        color = DarkBlueColor
                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.advertisement_1),
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreenContent()
}