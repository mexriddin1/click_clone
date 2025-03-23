@file:Suppress("UNUSED_EXPRESSION")

package com.example.mobilebankingcompose.screen.home.transfer.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.mobilebankingcompose.core.ui.theme.TextGray
import com.example.mobilebankingcompose.screen.auth.component.TopAppTitle
import com.example.mobilebankingcompose.screen.home.transfer.component.CardMaskInputForSearch
import com.example.mobilebankingcompose.screen.home.transfer.component.UserItem
import com.example.preseneter.transfer.TransferContract
import com.example.preseneter.transfer.TransferViewModel

class TransferScreen : Tab {
    override val options: TabOptions
        @Composable get() {
            val title = "O'tkazmalar"
            val icon = painterResource(R.drawable.up_down)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon,
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: TransferContract.ViewModel = getViewModel<TransferViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        TransferScreenContent(uiState, viewModel::onAction)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferScreenContent(
    uiState: State<TransferContract.UiState> = remember { mutableStateOf(TransferContract.UiState()) },
    onAction: (intent: TransferContract.Intent) -> Unit = {},
) {

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .imePadding(),
        topBar = {
        TopAppBar(colors = androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = DarkBackground
        ), title = {
            TopAppTitle(R.string.sent)
        })
    }) { innerPadding ->

        var cardNumber by remember { mutableStateOf("") }
        var _cardNumber by remember { mutableStateOf("") }

        if (cardNumber.length == 16 && cardNumber != _cardNumber) {
            _cardNumber = cardNumber
            onAction.invoke(TransferContract.Intent.SearchUser(cardNumber))
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground)
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            CardMaskInputForSearch(
                onTextChange = {
                    cardNumber = it
                },
                hintText = "Karta, hamyon yoki telefon raqam",
                borderSize = 1.dp,
                hintSize = 13.5f,
                errorMessage = uiState.value.isFound
            )
            if (uiState.value.user.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(50.dp))
                        if (uiState.value.isLoading) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier
                                    .size(30.dp)
                                    .height(40.dp),
                                strokeWidth = 2.5.dp
                            )
                        } else {
                            Icon(
                                painter = painterResource(if (uiState.value.isFound) R.drawable.transfer_number_icon else R.drawable.transfer_search),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                                    .size(60.dp),
                                tint = TextGray
                            )


                            Text(
                                text = if (uiState.value.isFound) "Oluvchining karta/hamyon/telfon\nraqamini to'liq kiriting." else "Karta hamyon yoki telfon raqamini bo'yicha mos\nqabul qiluvchi topilmadi.",
                                style = TextStyle(
                                    color = TextGray,
                                    fontFamily = FontFamily(Font(R.font.euclid_circular_regular)),
                                    fontSize = 17.sp
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.value.user) { item ->
                        UserItem(item.name, item.number, click = {
                            onAction.invoke(
                                TransferContract.Intent.OpenUser(
                                    com.example.core.module.transfer.TransferUser(
                                        item.name, item.number
                                    )
                                )
                            )
                        })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TransferScreenPreview() {
    TransferScreenContent()
}