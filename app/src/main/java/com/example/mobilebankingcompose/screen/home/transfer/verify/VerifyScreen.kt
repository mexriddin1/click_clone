package com.example.mobilebankingcompose.screen.home.transfer.verify

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.ComponentMargin
import com.example.mobilebankingcompose.core.ui.theme.ComponentSize
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.mobilebankingcompose.screen.auth.component.BottomSheetCustom
import com.example.mobilebankingcompose.screen.auth.component.InputTitle
import com.example.mobilebankingcompose.screen.auth.component.ResendButton
import com.example.mobilebankingcompose.screen.auth.component.TopAppTitle
import com.example.mobilebankingcompose.screen.auth.verify_code.constant.InputCode
import com.example.preseneter.transfer.VerifyTransferContract
import com.example.preseneter.transfer.VerifyTransferViewModel
import kotlinx.coroutines.launch

class VerifyCodeScreen : Screen {
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    override fun Content() {
        val viewModel: VerifyTransferContract.ViewModel =
            getViewModel<VerifyTransferViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        val context = LocalContext.current

        scope.launch {
            viewModel.message.collect {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        VerifyCodeScreenContent(uiState, viewModel::onAction)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyCodeScreenContent(
    uiState: State<VerifyTransferContract.UiState> = remember {
        mutableStateOf(
            VerifyTransferContract.UiState()
        )
    }, onAction: (intent: VerifyTransferContract.Intent) -> Unit = {}
) {
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var count by rememberSaveable { mutableIntStateOf(0) }

    Text(text = count.toString(), color = Color.Transparent)

    BottomSheetCustom(isBottomSheetVisible = isBottomSheetVisible,
        sheetState = sheetState,
        onDismiss = {
            isBottomSheetVisible = false
        }, changing = {
            count += 1
        })

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(DarkBackground), topBar = {
        TopAppBar(
            colors = androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = DarkBackground
            ), title = {
                TopAppTitle(R.string.code_verification)
            }, navigationIcon = {})
    }) { innerPadding ->
        var code by remember { mutableStateOf("") }
        var show by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 15.dp, end = 15.dp, bottom = 100.dp)
            ) {
                InputTitle(
                    R.string.enter_code,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(ComponentMargin.TextPadding)
                )

                InputCode(
                    onTextChange = {
                        code = it
                    },
                    finishTimer = {
                        show = true
                    },
                    errorMessage = "",

                    )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 20.dp)
            ) {
                if (show) {
                    ResendButton(
                        click = {

                        },
                        string = R.string.resend,
                        modifier = Modifier
                            .height(ComponentSize.AuthConstantHeight)
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    )
                }

                com.example.mobilebankingcompose.screen.auth.component.Button(
                    modifier = Modifier
                        .height(ComponentSize.AuthConstantHeight)
                        .fillMaxWidth(),
                    click = {
                        onAction.invoke(
                            VerifyTransferContract.Intent.CheckCode(code)
                        )
                    }, string = R.string.sent,
                    isLoading = uiState.value.isLoading,
                    isEnable = code.isNotBlank()
                )
            }
        }
    }
}