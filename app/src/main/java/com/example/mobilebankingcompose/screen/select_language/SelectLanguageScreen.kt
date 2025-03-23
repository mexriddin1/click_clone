package com.example.mobilebankingcompose.screen.select_language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.mobilebankingcompose.screen.select_language.component.SelectScreenItem
import com.example.preseneter.select_language.SelectLanguageContract
import com.example.preseneter.select_language.SelectLanguageViewModel

class SelectLanguageScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: SelectLanguageContract.ViewModel = getViewModel<SelectLanguageViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            viewModel.autoChangeLanguage(context)
        }

        SelectLanguageContent(
            uiState, viewModel::onAction
        )
    }
}

@Composable
fun SelectLanguageContent(
    uiState: State<SelectLanguageContract.UiState> = remember {
        mutableStateOf(
            SelectLanguageContract.UiState()
        )
    }, onAction: (intent: SelectLanguageContract.Intent) -> Unit = {}
) {

    Text(text = uiState.value.selectText, color = Color.Transparent)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.select_language),
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    fontSize = 25.sp, color = Cerulean, fontFamily = FontFamily(
                        Font(
                            resId = R.font.euclid_circular_bold
                        )
                    )
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        ) {
            val context = LocalContext.current
            SelectScreenItem(R.drawable.uz_flag_oval, "O'zbek") {
                onAction.invoke(
                    SelectLanguageContract.Intent.ClickItem(
                        context = context, language = com.example.core.module.main.Language.UZB
                    )
                )
            }
            SelectScreenItem(R.drawable.ru_flag_oval, "Русский") {
                onAction.invoke(
                    SelectLanguageContract.Intent.ClickItem(
                        context = context, language = com.example.core.module.main.Language.RUS
                    )
                )
            }
            SelectScreenItem(R.drawable.en_flag_oval, "English") {
                onAction.invoke(
                    SelectLanguageContract.Intent.ClickItem(
                        context = context, language = com.example.core.module.main.Language.ENG
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SelectLanguagePreview() {
    SelectLanguageContent()
}


