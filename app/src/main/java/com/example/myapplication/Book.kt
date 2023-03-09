package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.json.JSONArray
import org.json.JSONObject

class Book(
    private val title: String?,
    private val authors: JSONArray?,
    private val description: String?,
    private val categories: JSONArray?,
    private val saleInfo: JSONObject?,
    private val pageCount: Int?,
    private val imgUrl: String?) {

    @Composable
    fun Component() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (imgUrl != null) {
                AsyncImage(
                    model = imgUrl,
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(20.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (title != null) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )
                    }
                    if (authors != null) {
                        Text(
                            text = "저자: $authors",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(horizontal = 20.dp),
                        )
                    }
                    if (saleInfo != null) {
                        Text(
                            text = saleInfo.getString("saleability"),
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(horizontal = 20.dp),
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (description != null) {
                        Text(
                            text = description,
                            fontWeight = FontWeight.Light,
                            fontSize = 20.sp
                        )
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        ) {
                            Text("번역하기")
                        }
                    }
                    if (categories != null) {
                        Text(
                            text = "카테고리: $categories",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(horizontal = 20.dp),
                        )
                    }
                    if (pageCount != null) {
                        Text(
                            text = pageCount.toString() + "쪽",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(horizontal = 20.dp),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Component() {
    val title = "google"
    val authors = JSONArray(arrayOf("superman"))
    val description = "대충 설명"
    val categories = JSONArray(arrayOf("과학"))
    val pageCount = 200
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.search_logo),
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
//                .padding(20.dp)
                .weight(1f),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Text(
                    text = "저자: $authors",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                )
                Text(
                    text = "NOT_SALE",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = description,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp
                )
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                ) {
                    Text("번역하기")
                }
                Text(
                    text = "카테고리: $categories",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                )
                Text(
                    text = pageCount.toString() + "쪽",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                )
            }
        }
    }
}