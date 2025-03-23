package com.example.mobilebankingcompose.screen.auth.register

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.mobilebankingcompose.screen.auth.component.InputText
import com.example.mobilebankingcompose.screen.auth.component.InputTitle
import com.example.mobilebankingcompose.screen.auth.component.LoadIconBar
import com.example.mobilebankingcompose.screen.auth.component.TopAppTitle
import com.example.mobilebankingcompose.screen.auth.register.constant.DateVisualTransformation
import com.example.mobilebankingcompose.screen.auth.register.constant.RadioButtons
import com.example.mobilebankingcompose.screen.auth.register.constant.YearInput
import com.example.mobilebankingcompose.screen.auth.register.constant.checkRegister
import com.example.mobilebankingcompose.screen.auth.register.constant.convertDateToLong
import com.example.preseneter.auth.RegisterContract
import com.example.preseneter.auth.RegisterContract.UiState
import com.example.preseneter.auth.RegisterViewModel
import kotlinx.coroutines.launch

class RegisterScreen : Screen {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    override fun Content() {
        val viewModel: RegisterContract.ViewModel = getViewModel<RegisterViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        val context = LocalContext.current

        scope.launch {
            viewModel.sideEffect.collect {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        RegisterScreenContent(uiState, viewModel::onAction)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenContent(
    uiState: State<UiState> = remember { mutableStateOf(UiState()) },
    onAction: (intent: RegisterContract.Intent) -> Unit = {}
) {
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var count by rememberSaveable { mutableIntStateOf(0) }

    Text(text = count.toString(), color = Color.Transparent)

    BottomSheetCustom(isBottomSheetVisible = isBottomSheetVisible,
        sheetState = sheetState,
        onDismiss = {
            isBottomSheetVisible = false
        },
        changing = { count += 1 })

    key(count) {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(DarkBackground), topBar = {
            TopAppBar(colors = androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = DarkBackground
            ), modifier = Modifier.background(DarkBackground), title = {
                TopAppTitle(R.string.register)
            }, navigationIcon = {
                LoadIconBar {
                    isBottomSheetVisible = true
                }
            })
        }) { innerPadding ->
            var surname by remember { mutableStateOf("") }
            var lastname by remember { mutableStateOf("") }
            var birthday by remember { mutableStateOf("") }
            var gender by remember { mutableIntStateOf(0) }
            var phone by remember { mutableStateOf("") }
            var pasword by remember { mutableStateOf("") }
            var passwordAgain by remember { mutableStateOf("") }
            val scrollState = rememberScrollState()

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBackground)
                        .padding(innerPadding),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(ComponentMargin.HorizontalPadding)
                            .verticalScroll(scrollState),
                    ) {
                        InputTitle(
                            R.string.enter_surname,
                            modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)
                        )
                        InputText(onTextChange = {
                            surname = it
                        }, errorMessage = uiState.value.surnameError)

                        InputTitle(
                            R.string.enter_lastname,
                            modifier = Modifier.padding(ComponentMargin.TextPadding)
                        )
                        InputText(onTextChange = {
                            lastname = it
                        }, errorMessage = uiState.value.lastnameError)

                        InputTitle(
                            R.string.enter_birth_day,
                            modifier = Modifier.padding(ComponentMargin.TextPadding)
                        )
                        YearInput(
                            visualTransformation = DateVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                            ), onTextChange = {
                                if (it.isNotBlank()) {
                                    birthday = (convertDateToLong(it) ?: "")
                                }
                            }, errorMessage = uiState.value.birthdayError
                        )

                        InputTitle(
                            R.string.enter_your_gender,
                            modifier = Modifier.padding(ComponentMargin.TextPadding)
                        )
                        RadioButtons {
                            gender = it
                        }

                        InputTitle(
                            R.string.enter_phone_number,
                            modifier = Modifier
                                .padding(ComponentMargin.TextPadding)
                        )
                        InputPhone(onTextChange = {
                            phone = it
                        }, imeAction = ImeAction.Next, errorMessage = uiState.value.phoneError)

                        InputTitle(
                            R.string.enter_password,
                            modifier = Modifier.padding(ComponentMargin.TextPadding)
                        )
                        InputPassword(onTextChange = {
                            pasword = it
                        }, imeAction = ImeAction.Next, errorMessage = uiState.value.passwordError)

                        InputTitle(
                            R.string.enter_password_again,
                            modifier = Modifier.padding(ComponentMargin.TextPadding)
                        )
                        InputPassword(
                            onTextChange = {
                                passwordAgain = it
                            },
                            imeAction = ImeAction.Done,
                            errorMessage = uiState.value.passwordAgainError
                        )
                    }

                    Spacer(modifier = Modifier.height(200.dp))

                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(ComponentMargin.HorizontalPadding)
                        .background(DarkBackground)
                ) {
                    Text(
                        text = stringResource(R.string.register_text),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp, top = 10.dp),
                        style = TextStyle(
                            color = TextGray,
                            fontSize = (13.5).sp,
                            fontFamily = FontFamily(Font(R.font.lato_regular))
                        )
                    )
                    val bool = checkRegister(
                        com.example.core.module.auth.RegisterData(
                            firstName = surname,
                            lastName = lastname,
                            birthDate = birthday,
                            gender = gender.toString(),
                            phone = phone,
                            password = pasword
                        ), passwordAgain
                    )
                    com.example.mobilebankingcompose.screen.auth.component.Button(
                        {
                            if (bool) {
                                onAction.invoke(
                                    RegisterContract.Intent.RegisterUser(
                                        com.example.core.module.auth.RegisterData(
                                            firstName = surname,
                                            lastName = lastname,
                                            birthDate = birthday,
                                            gender = gender.toString(),
                                            phone = phone,
                                            password = pasword
                                        )
                                    )
                                )
                            }
                        }, R.string.keep_going, modifier = Modifier
                            .height(ComponentSize.AuthConstantHeight)
                            .fillMaxWidth(),
                        isLoading = uiState.value.isLoading,
                        isEnable = bool
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}