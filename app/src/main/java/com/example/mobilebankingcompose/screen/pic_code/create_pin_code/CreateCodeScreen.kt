@file:Suppress("NAME_SHADOWING")

package com.example.mobilebankingcompose.screen.pic_code.create_pin_code

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.ComponentRadius
import com.example.mobilebankingcompose.core.ui.theme.ComponentTextStyle
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.mobilebankingcompose.screen.auth.component.TopAppTitleCode
import com.example.mobilebankingcompose.screen.pic_code.constant.CodeItem
import com.example.preseneter.pin_code.CreateCodeContract
import com.example.preseneter.pin_code.CreateCodeViewModel

class CreateCodeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: CreateCodeContract.ViewModel = getViewModel<CreateCodeViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        CreateCodeContent(uiState, viewModel::onAction)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCodeContent(
    uiState: State<CreateCodeContract.UiState> = remember { mutableStateOf(CreateCodeContract.UiState()) },
    onAction: (intent: CreateCodeContract.Intent) -> Unit = {}
) {
    var codeType by remember { mutableStateOf(false) }
    var codeText by remember { mutableStateOf("") }

    if (codeText.length == 5) onAction.invoke(CreateCodeContract.Intent.SetPinCode(codeText))

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(DarkBackground), topBar = {
        TopAppBar(
            colors = androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = DarkBackground
            ), title = {
                TopAppTitleCode(R.string.authorization)
            }, navigationIcon = {
                Image(painter = painterResource(R.drawable.logout),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 15.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onAction.invoke(CreateCodeContract.Intent.OpenLoginScreen)
                        })
            })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(DarkBackground)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.create_pin_code),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 15.dp),
                    style = ComponentTextStyle.PinCodeText
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp)
                    .weight(2f)
            ) {
                Image(painter = painterResource(if (codeType) R.drawable.eye else R.drawable.hide_eye),
                    contentDescription = null,
                    modifier = Modifier
                        .width(37.dp)
                        .height(20.dp)
                        .padding(start = 15.dp)
                        .align(Alignment.CenterStart)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            codeType = !codeType
                        })

                Row(modifier = Modifier.align(Alignment.Center)) {
                    if (codeType) {
                        Text(
                            text = codeText, style =
                            ComponentTextStyle.CodeTextBlueStyle
                        )
                    } else {
                        for (i in 0..4) {
                            if (codeText.length > i) {
                                Surface(
                                    modifier = Modifier
                                        .size(12.dp),
                                    shape = ComponentRadius.medium,
                                    color = Cerulean
                                ) {

                                }
                            } else {
                                Surface(
                                    modifier = Modifier
                                        .size(12.dp)
                                        .border(
                                            width = 0.7.dp,
                                            color = Cerulean,
                                            shape = ComponentRadius.medium
                                        ),
                                    shape = ComponentRadius.medium,
                                    color = Color.Transparent
                                ) {

                                }
                            }
                            Spacer(modifier = Modifier.padding(end = 8.dp))
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .align(Alignment.Center)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.width(310.dp)
                    ) {
                        items(12) { number ->
                            when (number + 1) {
                                10 -> {}

                                11 -> {
                                    CodeItem("0", click = { _, number ->
                                        if (codeText.length <= 4) codeText += number
                                    })
                                }

                                12 -> {
                                    CodeItem(
                                        id = if (codeText.isEmpty()) R.drawable.delete else R.drawable.delete_white,
                                        longClick = {
                                            codeText = ""
                                        },
                                        click = { id, _ ->
                                            if (codeText.isNotEmpty()) codeText =
                                                codeText.substring(0, codeText.length - 1)
                                        },
                                        size = 45.dp
                                    )
                                }

                                else -> CodeItem((number + 1).toString(), click = { _, number ->
                                    if (codeText.length <= 4) codeText += number
                                })
                            }
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.2f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateCodePreview() {
    CreateCodeContent()
}