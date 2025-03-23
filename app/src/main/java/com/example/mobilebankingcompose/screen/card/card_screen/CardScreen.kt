package com.example.mobilebankingcompose.screen.card.card_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.preseneter.card.CardContract
import com.example.preseneter.card.CardViewModel

class CardScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: CardContract.ViewModel = getViewModel<CardViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        CardScreenContent(uiState, viewModel::onAction)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScreenContent(
    uiState: State<CardContract.UiState> = remember { mutableStateOf(CardContract.UiState()) },
    onAction: (CardContract.Intent) -> Unit = {}
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(DarkBackground), topBar = {
        TopAppBar(colors = androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = DarkBackground
        ), title = {
            Text(
                text = stringResource(R.string.my_wallet), modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.euclid_circular_bold)
                    ), fontSize = 17.sp, color = Color.White
                )
            )
        }, navigationIcon = {
            Icon(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(22.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onAction.invoke(CardContract.Intent.Back)
                    }

            )
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(DarkBackground)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

                if (uiState.value.isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.Center)
                    )
                } else {
                    if (uiState.value.cardList.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(uiState.value.cardList) {
                                com.example.mobilebankingcompose.screen.card.card_screen.component.Card(
                                    com.example.core.module.main.CardType.HUMO,
                                    cardName = it.name,
                                    cardNumber = it.pan,
                                    isMainCard = it.isMainCard,
                                    numberIsShow = it.isVisible,
                                    sum = it.amount.toString(),
                                    year = it.expiredYear.toString()
                                )
                            }
                        }
                    } else {
                        Column(modifier = Modifier.align(Alignment.Center)) {
                            Text("Cardtalar mavjud emas")
                        }
                    }
                }


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(DarkBackground)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    {},
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .height(44.dp)
                        .weight(1f)
                        .border(1.5.dp, Cerulean, RoundedCornerShape(18))
                        .padding(end = 4.dp),
                ) {
                    Text(
                        text = "O'tkazma", style = TextStyle(
                            color = Cerulean,
                            fontFamily = FontFamily(
                                Font(R.font.euclid_circular_regular)
                            ),
                            fontSize = 17.sp,
                        )
                    )
                }


                Button(
                    {
                        onAction.invoke(CardContract.Intent.OpenAddCard)
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Cerulean,
                        containerColor = Cerulean
                    ),
                    shape = RoundedCornerShape(18),
                    modifier = Modifier
                        .height(44.dp)
                        .weight(1f)
                        .padding(start = 4.dp),
                ) {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.add),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(20.dp)
                        )
                        Text(
                            text = "Karta qo'shish", style = TextStyle(
                                color = Color.White,
                                fontFamily = FontFamily(
                                    Font(R.font.euclid_circular_bold)
                                ),
                                fontSize = 17.sp,
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CardScreenPreview() {
    CardScreenContent()
}