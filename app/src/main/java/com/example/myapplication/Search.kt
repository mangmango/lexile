package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController

class Search {
    companion object {
        @OptIn(ExperimentalComposeUiApi::class)
        @Composable
        fun SearchScreen(navController: NavController) {
            val (search, setSearch) = remember { mutableStateOf("") }
            val (searchBook, setSearchBook) = remember {
                mutableStateOf("")
            }
//        val (histories, setHistories) = remember { mutableStateOf() }
            val keyboardController = LocalSoftwareKeyboardController.current

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Scaffold(
                    topBar = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = {
                                navController.navigate("main")
                            }) {
                                Icon(Icons.Default.KeyboardArrowLeft, null)
                            }
                            OutlinedTextField(
                                value = search,
                                onValueChange = { setSearch(it) },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Search
                                ),
                                placeholder = { Text("검색") },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.White,
                                    unfocusedBorderColor = Color.White,
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                keyboardActions = KeyboardActions(onSearch = {
                                    if (search.isNotBlank()) {
                                        setSearchBook(search)

                                        navController.navigate("search_result/$search")

                                        setSearch("")
                                        keyboardController?.hide()
                                    }
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
}