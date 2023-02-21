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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

class Search {
    @Composable
    fun SearchScreen(onNavigateToMain: () -> Unit) {
        val (search, setSearch) = remember { mutableStateOf("") }

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
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            placeholder = { Text("검색") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                            ),
                            modifier = Modifier
                                .fillMaxWidth(),
                            keyboardActions = KeyboardActions(onDone = {
                                println("test")
                            })
                        )
                    }
                }
            ) {
                Histories(paddingValues = it)
            }
        }
    }

    @Composable
    fun Histories(paddingValues: PaddingValues) {
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            Text("Oh")
        }
    }
}