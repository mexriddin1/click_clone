package com.example.mobilebankingcompose.screen.auth.login

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.runtime.key
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.ComponentMargin
import com.example.mobilebankingcompose.core.ui.theme.ComponentSize
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.mobilebankingcompose.core.ui.theme.TextGray
import com.example.mobilebankingcompose.screen.auth.component.BottomSheetCustom
import com.example.mobilebankingcompose.screen.auth.component.InputPassword
import com.example.mobilebankingcompose.screen.auth.component.InputPhone
import com.example.mobilebankingcompose.screen.auth.component.InputTitle
import com.example.mobilebankingcompose.screen.auth.component.LoadIconBar
import com.example.mobilebankingcompose.screen.auth.component.TopAppTitle
import com.example.mobilebankingcompose.screen.auth.login.Componest.checkLogin
import com.example.preseneter.auth.LoginContract
import com.example.preseneter.auth.LoginViewModel
import kotlinx.coroutines.launch

class LoginScreen : Screen {

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    override fun Content() {
        val viewModel: LoginContract.ViewModel = getViewModel<LoginViewModel>()
        val uiStateState = viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        val context = LocalContext.current

        scope.launch {
            viewModel.sideEffect.collect {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        LoginScreenContent(
            uiStateState, viewModel::onAction
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(
    uiState: State<LoginContract.UiState> = remember { mutableStateOf(LoginContract.UiState()) },
    onAction: (intent: LoginContract.Intent) -> Unit = {}
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

    key(count) {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(DarkBackground), topBar = {
            TopAppBar(
                colors = androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = DarkBackground
                ), title = {
                    TopAppTitle(R.string.authorization)
                }, navigationIcon = {
                    LoadIconBar {
                        isBottomSheetVisible = true
                    }
                })
        }) { innerPadding ->
            var phone by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBackground)
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 15.dp, bottom = 100.dp, end = 15.dp)
                ) {
                    InputTitle(
                        R.string.enter_phone_number,
                        modifier = Modifier.padding(ComponentMargin.TextPadding)
                    )
                    InputPhone(onTextChange = {
                        Log.d("Phone", "LoginScreenContent: $it")
                        phone = it
                    }, imeAction = ImeAction.Next)

                    InputTitle(
                        R.string.enter_password,
                        modifier = Modifier.padding(ComponentMargin.TextPadding)
                    )
                    InputPassword({
                        password = it
                    }, imeAction = ImeAction.Done)
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(ComponentMargin.HorizontalPadding)
                ) {
                    Text(
                        text = stringResource(R.string.register_text),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                onAction.invoke(LoginContract.Intent.OpenRegister)
                            },
                        style = TextStyle(
                            color = TextGray,
                            fontSize = (13.5).sp,
                            fontFamily = FontFamily(Font(R.font.lato_regular))
                        )
                    )

                    com.example.mobilebankingcompose.screen.auth.component.Button(
                        modifier = Modifier
                            .height(ComponentSize.AuthConstantHeight)
                            .fillMaxWidth(),
                        click = {
                            if (checkLogin(phone, password)) {
                                onAction.invoke(
                                    LoginContract.Intent.LoginUser(
                                        com.example.core.module.auth.LoginData(
                                            phone,
                                            password
                                        )
                                    )
                                )
                            }
                        }, string = R.string.keep_going,
                        isLoading = uiState.value.isLoading,
                        isEnable = checkLogin(phone, password)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}