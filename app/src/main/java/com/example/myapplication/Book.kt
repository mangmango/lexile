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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp),
            ) {
            if (imgUrl != null) {
                Image(
                    painter = rememberAsyncImagePainter(imgUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .wrapContentHeight()
                        .wrapContentWidth()
                )
            }
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
                            .padding(horizontal = 10.dp)) {
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

@Preview
@Composable
fun Component() {
    val title = "google"
    val authors = JSONArray(arrayOf("superman"))
    val description = "대충 설명"
    val categories = JSONArray(arrayOf("과학"))
    val pageCount = 200
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp),
    ) {
        Image(
            painter = rememberAsyncImagePainter("http://books.google.com/books/content?id=ppf9DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"),
            contentDescription = null,
            modifier = Modifier
                .wrapContentSize()
                .wrapContentHeight()
                .wrapContentWidth()
        )
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
                    .padding(horizontal = 10.dp)) {
                Text("번역하기")
            }
            Text(
                text = "카테고리: $categories",
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