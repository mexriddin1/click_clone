package com.example.mobilebankingcompose.screen.card.add_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.core.module.card.NewCardDate
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.ComponentColor
import com.example.mobilebankingcompose.core.ui.theme.ComponentMargin
import com.example.mobilebankingcompose.core.ui.theme.ComponentRadius
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.mobilebankingcompose.core.ui.theme.TextGray
import com.example.mobilebankingcompose.screen.card.add_screen.component.CardMaskInput
import com.example.mobilebankingcompose.screen.card.add_screen.component.CardNameMaskInput
import com.example.mobilebankingcompose.screen.card.add_screen.component.CustomSwitch
import com.example.mobilebankingcompose.screen.card.add_screen.component.YearMaskInput
import com.example.preseneter.card.AddCardContract
import com.example.preseneter.card.AddCardViewModel

class AddScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<AddCardViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        AddScreenContent(uiState, viewModel::onAction)
    }
}

@SuppressLint("ShowToast")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenContent(
    uiState: State<AddCardContract.UiState> = remember { mutableStateOf(AddCardContract.UiState()) },
    onAction: (AddCardContract.Intent) -> Unit = {}
) {

    var isChecked by remember { mutableStateOf(false) }
    var cardNumber by remember { mutableStateOf("") }
    var cardName by remember { mutableStateOf("") }
    var cardYear by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(containerColor = DarkBackground,
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(DarkBackground),
        topBar = {
            TopAppBar(colors = androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = DarkBackground
            ), title = {
                Text(
                    text = stringResource(R.string.my_wallet),
                    modifier = Modifier
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
                Icon(painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(22.dp)
                        .clickable(indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {
                            onAction.invoke(AddCardContract.Intent.Back)
                        }
                )
            })
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(ComponentMargin.HorizontalPadding)
                .fillMaxSize()
                .background(DarkBackground)
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .clip(ComponentRadius.regular)
                        .background(ComponentColor)
                        .padding(16.dp)
                ) {
                    Text(
                        "Karta raqami", style = TextStyle(
                            color = TextGray,
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.euclid_circular_regular))
                        ), modifier = Modifier.padding(bottom = 3.dp)
                    )
                    CardMaskInput(onTextChange = {
                        cardNumber = it
                    }, hintText = "0000 0000 0000 0000")
                    Text(
                        "Amal qilish muddati", style = TextStyle(
                            color = TextGray,
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.euclid_circular_regular))
                        ), modifier = Modifier.padding(bottom = 3.dp, top = 25.dp)
                    )
                    YearMaskInput(onTextChange = {
                        cardYear = it
                    }, hintText = "OO/YY")

                    Text(
                        "Karta nomi(shartemas)", style = TextStyle(
                            color = TextGray,
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.euclid_circular_regular))
                        ), modifier = Modifier.padding(bottom = 3.dp, top = 25.dp)
                    )
                    CardNameMaskInput(onTextChange = {
                        cardName = it
                    }, hintText = "Masalan: Asosiy")
                }
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.info),
                        tint = Cerulean,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 10.dp)
                    )
                    Text(
                        "Kartani asosiyga aylantirish", style = TextStyle(
                            color = Color.White,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.euclid_circular_regular))
                        )
                    )
                    Spacer(
                        Modifier
                            .height(10.dp)
                            .weight(1f)
                    )

                    CustomSwitch(checked = isChecked, {
                        isChecked = !isChecked
                    })
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .align(Alignment.BottomCenter)
                    .background(DarkBackground), verticalAlignment = Alignment.CenterVertically
            ) {
                com.example.mobilebankingcompose.screen.auth.component.Button(
                    {
                        if (checkButton(number = cardNumber, year = cardYear)) {
                            onAction.invoke(
                                AddCardContract.Intent.AddNewCard(
                                    NewCardDate(
                                        pan = cardNumber,
                                        expiredYear = "20" + cardYear.substring(2, 4),
                                        expiredMonth = cardYear.substring(0, 2),
                                        name = cardName,
                                    )
                                )
                            )

                            Toast.makeText(
                                context,
                                "$cardNumber == ${
                                    "20" + cardYear.substring(2, 4)
                                } == $cardName == ${cardYear.substring(0, 2)}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    R.string.sent,
                    isEnable = checkButton(number = cardNumber, year = cardYear),
                    isLoading = uiState.value.isLoading,
                    modifier = Modifier
                        .height(44.dp)
                        .weight(1f)
                )
            }
        }
    }
}

fun checkButton(
    number: String,
    year: String
): Boolean {
    return if (year.length == 4) {
        number.length == 16 && year.substring(2).toInt() >= 12
    } else false
}

@Preview
@Composable
fun AddScreenPreview() {
    AddScreenContent()
}