package com.example.mobilebankingcompose.screen.auth.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.entity.local.LocationHelper
import com.example.mobilebankingcompose.screen.select_language.component.SelectScreenItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetCustom(
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    changing: () -> Unit = {}
) {
    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = DarkBackground,
            contentColor = Cerulean,
            shape = RoundedCornerShape(7, 7, 0, 0),
            scrimColor = Color.White.copy(alpha = .3f),
            windowInsets = WindowInsets(0, 0, 0, 0),
        ) {
            var currentLanguage by remember { mutableStateOf(LocationHelper.getLocation().country) }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBackground)
            ) {
                Text(
                    text = stringResource(R.string.select_language), style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.euclid_circular_bold))
                    ), modifier = Modifier.padding(start = 15.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp, top = 40.dp)
                        .align(Alignment.Center),
                ) {
                    SelectScreenItem(
                        R.drawable.uz_flag_oval,
                        "O'zbek",
                        language = com.example.core.module.main.Language.UZB,
                        isHideRadioBottom = true,
                        buttonClick = {
                            changing.invoke()
                            currentLanguage = com.example.core.module.main.Language.UZB.country
                        },
                        isSelected = currentLanguage == com.example.core.module.main.Language.UZB.country
                    ) {
                        changing.invoke()
                        currentLanguage = com.example.core.module.main.Language.UZB.country
                    }
                    SelectScreenItem(
                        R.drawable.ru_flag_oval,
                        "Русский",
                        language = com.example.core.module.main.Language.RUS,
                        isHideRadioBottom = true,
                        buttonClick = {
                            changing.invoke()
                            currentLanguage = com.example.core.module.main.Language.RUS.country
                        },
                        isSelected = currentLanguage == com.example.core.module.main.Language.RUS.country
                    ) {
                        changing.invoke()
                        currentLanguage = com.example.core.module.main.Language.RUS.country
                    }
                    SelectScreenItem(
                        R.drawable.en_flag_oval,
                        "English",
                        language = com.example.core.module.main.Language.ENG,
                        isHideRadioBottom = true,
                        buttonClick = {
                            changing.invoke()
                            currentLanguage = com.example.core.module.main.Language.ENG.country
                        },
                        isSelected = currentLanguage == com.example.core.module.main.Language.ENG.country
                    ) {
                        changing.invoke()
                        currentLanguage = com.example.core.module.main.Language.ENG.country
                    }
                }
            }

        }
    }
}


