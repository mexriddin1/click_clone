package com.example.mobilebankingcompose.screen.home.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.core.module.transfer.ReportsData
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.ComponentColor
import com.example.mobilebankingcompose.core.ui.theme.ComponentRadius
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.mobilebankingcompose.core.ui.theme.TextGray
import com.example.mobilebankingcompose.screen.home.reports.component.ReportsItem
import com.example.preseneter.home.ReportsContract
import com.example.preseneter.home.ReportsViewModel

class ReportsScreen : Tab {
    override val options: TabOptions
        @Composable get() {
            val title = "Hisobot"
            val icon = painterResource(R.drawable.pie_chart)

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
        val pageCount = remember { mutableIntStateOf(1) }
        val viewModel: ReportsContract.ViewModel = getViewModel<ReportsViewModel>()
        val uiState = viewModel.uiState.collectAsState()

        ReportsScreenContent(
            uiState,
            viewModel.getHistory(size = 10, pageCount = pageCount.value).collectAsLazyPagingItems(),
            pageCount,
            viewModel::onAction,
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreenContent(
    uiState: State<ReportsContract.UiState> = remember { mutableStateOf(ReportsContract.UiState()) },
    lazyItemsData: LazyPagingItems<com.example.core.module.Transaction>,
    pageCount: MutableState<Int>,
    onAction: (ReportsContract.Intent) -> Unit = {}
) {
    var state by remember { mutableIntStateOf(1) }

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .imePadding()
        .background(DarkBackground)
        .padding(horizontal = 16.dp), topBar = {
        TopAppBar(
            colors = androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = DarkBackground
            ), title = {
                Text(
                    text = "Hisobot",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 40.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.euclid_circular_medium)
                        ), fontSize = 18.sp, color = Color.White
                    )
                )
            })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground)
        ) {
            Row(
                modifier = Modifier
                    .padding(innerPadding)
                    .height(44.dp)
                    .clip(ComponentRadius.medium)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .background(if (state == 1) Cerulean else ComponentColor)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            state = 1
                        }

                ) {
                    Text(
                        "Kiruvchi", modifier = Modifier.align(Alignment.Center), style = TextStyle(
                            color = if (1 != state) TextGray else Color.White,
                            fontFamily = FontFamily(Font(R.font.euclid_circular_medium)),
                            fontSize = 16.sp
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .background(if (state == 2) Cerulean else ComponentColor)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            state = 2
                        }

                ) {
                    Text(
                        "Chiquvchi", modifier = Modifier.align(Alignment.Center), style = TextStyle(
                            color = if (2 != state) TextGray else Color.White,
                            fontFamily = FontFamily(Font(R.font.euclid_circular_medium)),
                            fontSize = 16.sp
                        )
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(top = 25.dp)
            ) {
                if (uiState.value.isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    if (state == 1) {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            items(
                                lazyItemsData.itemCount,
//                                key = lazyItemsData.itemKey { it.time },
                                contentType = lazyItemsData.itemContentType { "contentType" }
                            ) {
                                val item = lazyItemsData[it]
                                if (item != null) {
                                    ReportsItem(
                                        ReportsData(
                                            item.from,
                                            item.amount.toString(),
                                            item.time
                                        )
                                    )
                                }
                            }
                        }


                    } else {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            items(
                                lazyItemsData.itemCount,
//                                key = lazyItemsData.itemKey { it.time },
                                contentType = lazyItemsData.itemContentType { "contentType" }
                            ) {
                                val item = lazyItemsData[it]
                                if (item != null) {
                                    ReportsItem(
                                        ReportsData(
                                            item.from,
                                            item.amount.toString(),
                                            item.time
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}