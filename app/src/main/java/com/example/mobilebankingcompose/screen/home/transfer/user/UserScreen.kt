package com.example.mobilebankingcompose.screen.home.transfer.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.core.module.transfer.TransferUser
import com.example.entity.remote.transfer.request.TransactionRequest
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.mobilebankingcompose.core.ui.theme.TextGray
import com.example.mobilebankingcompose.screen.card.card_screen.component.maskCardNumber
import com.example.mobilebankingcompose.screen.home.transfer.component.TransferInput
import com.example.preseneter.transfer.UserContract
import com.example.preseneter.transfer.UserViewModel

class UserScreen(
    private val user: TransferUser
) : Screen {

    @Composable
    override fun Content() {
        val viewModel: UserContract.ViewModel =
            getViewModel<UserViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        UserScreenContent(uiState, viewModel::onAction, user)
    }

}

@Composable
fun UserScreenContent(
    uiState: State<UserContract.UiState> = remember {
        mutableStateOf(
            UserContract.UiState()
        )
    }, onAction: (intent: UserContract.Intent) -> Unit = {},
    user: TransferUser
) {
    var sum by remember { mutableStateOf("") }
    var lastCard by remember { mutableIntStateOf(-1) }

    Column(
        modifier = Modifier
            .imePadding()
            .background(DarkBackground)
            .statusBarsPadding()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .shadow(1.dp)
                .padding(16.dp),

            ) {

            Icon(
                painter = painterResource(R.drawable.back),
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onAction.invoke(UserContract.Intent.Back)
                    }
            )

            Spacer(modifier = Modifier.width(20.dp))

            Image(
                painter = painterResource(R.drawable.user),
                contentDescription = null,
                modifier = Modifier.size(45.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = user.name, style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.euclid_circular_medium)),
                        fontSize = 17.sp
                    ), modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = maskCardNumber(user.number),
                    style = TextStyle(
                        color = TextGray,
                        fontFamily = FontFamily(Font(R.font.euclid_circular_regular)),
                        fontSize = 15.sp
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            LazyRow(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(uiState.value.cardList) {
                    com.example.mobilebankingcompose.screen.card.card_screen.component.Card(
                        com.example.core.module.main.CardType.HUMO,
                        cardName = it.name,
                        cardNumber = it.pan,
                        isMainCard = it.id == lastCard,
                        numberIsShow = it.isVisible,
                        sum = it.amount.toString(),
                        year = it.expiredYear.toString(),
                        isSelection = false
                    ) {
                        lastCard = it.id
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .background(DarkBackground)
                .border(
                    0.1.dp,
                    TextGray,
                    shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp)
                )
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            TransferInput(onTextChange = {
                sum = it
            }, isLoading = uiState.value.isLoading, hintText = "Pul o'tkazish", click = {
                if (lastCard != -1) {
                    onAction.invoke(
                        UserContract.Intent.TransferSum(
                            TransactionRequest(
                                type = "third-card",
                                senderId = lastCard.toString(),
                                receiverPan = user.number,
                                amount = sum.toInt()
                            )
                        )
                    )
                }
            })
        }
    }
}