package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.json.JSONArray
import org.json.JSONObject

class Book(
    private val title: String,
    private val authors: JSONArray,
    private val description: String?,
    private val categories: JSONArray?,
    private val saleInfo: JSONObject,
    private val pageCount: Int) {

    @Composable
    fun book() {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color.White),

            ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Text(
                    text = authors.toString(),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                )
                Text(
                    text = saleInfo.getString("saleability"),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (description != null) {
                    Text(
                        text = description,
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp
                    )
                }
                Text(
                    text = categories.toString(),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                )
                Text(
                    text = pageCount.toString(),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                )
            }
        }
    }
}