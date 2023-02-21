package com.example.myapplication

import android.webkit.WebView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

class Search {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun SearchScreen(onNavigateToMain: () -> Unit) {
        val (search, setSearch) = remember { mutableStateOf("") }
        val (searchBook, setSearchBook) = remember {
            mutableStateOf("")
        }
//        val (histories, setHistories) = remember { mutableStateOf() }
        val keyboardController = LocalSoftwareKeyboardController.current

        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Scaffold(
                topBar = {
                    Row {
                        IconButton(onClick = {
                            onNavigateToMain()
                        }) {
                            Icon(Icons.Default.KeyboardArrowLeft, null)
                        }
                        OutlinedTextField(
                            value = search,
                            onValueChange = { setSearch(it) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
                            placeholder = { Text("검색") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                            ),
                            modifier = Modifier
                                .fillMaxWidth(),
                            keyboardActions = KeyboardActions(onSearch = {
                                setSearchBook(search)
//                                https://www.google.com/search?tbm=bks&q=quantum+mechanics


//                                val webViewState =
//                                    rememberWebViewState(
//                                        url = "www.naver.com",
//                                        additionalHttpHeaders = emptyMap()
//                                    )
//                                WebView(state = webViewState)
//                                addHistory(search)

                                setSearch("")
                                keyboardController?.hide()
                            })
                        )
                    }
                }
            ) {
                Histories(paddingValues = it, text = searchBook)
            }
        }
    }

    @Composable
    fun Histories(paddingValues: PaddingValues, text: String) {
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            Text(text)
        }
    }
}