package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SearchResult {
    companion object {
        @Composable
        fun SearchResult(q: String) {
            val books = getBooks(q).collectAsState(initial = emptyList())

            if (books.value.isNotEmpty()) {
                LazyColumn {
                    items(books.value.size) { i ->
                        books.value[i].Component()
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "책의 정보가 없습니다!", fontSize = 25.sp)
                }
            }
        }

        private fun getBooks(bookName: String): Flow<List<Book>> = flow {
            val books = mutableListOf<Book>()
            var notfound = false

            withContext(Dispatchers.IO) {
                val url = URL("https://www.googleapis.com/books/v1/volumes?q=${bookName.replace(" ", "+")}")
                val conn = url.openConnection() as HttpURLConnection

                conn.connectTimeout = 150000
                conn.readTimeout = 150000
                conn.requestMethod = "GET"
                conn.setRequestProperty("Content-Type", "application/json")

                val br = BufferedReader(InputStreamReader(conn.inputStream))
                val sb = StringBuilder()
                if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                    while (true) {
                        val line = br.readLine() ?: break
                        sb.append(line)
                    }
                }

                val json = JSONObject(sb.toString())

                if (!json.has("items")) notfound = true

                if (!notfound) {
                    val items = json.getJSONArray("items")

                    for (i in 0 until items.length()) {
                        val bookInfo = items.getJSONObject(i)
                        val volumeInfo = bookInfo.getJSONObject("volumeInfo")
                        var title: String? = null
                        var authors: JSONArray? = null
                        var description: String? = null
                        var categories: JSONArray? = null
                        var saleInfo: JSONObject? = null
                        var pageCount: Int? = null
                        var imgUrl: String? = null

                        if (volumeInfo.has("title")) title = volumeInfo.getString("title")
                        if (volumeInfo.has("authors")) authors = volumeInfo.getJSONArray("authors")
                        if (volumeInfo.has("description")) description = volumeInfo.getString("description")
                        if (volumeInfo.has("categories")) categories = volumeInfo.getJSONArray("categories")
                        if (bookInfo.has("saleInfo")) saleInfo = bookInfo.getJSONObject("saleInfo")
                        if (volumeInfo.has("pageCount")) pageCount = volumeInfo.getInt("pageCount")
                        if (volumeInfo.has("imageLinks")) imgUrl = volumeInfo.getJSONObject("imageLinks").getString("thumbnail")

                        val book = Book(title, authors, description, categories, saleInfo, pageCount, imgUrl)

                        books.add(book)
                    }
                }
            }

            if (notfound) {
                emit(emptyList())
            } else {
                emit(books)
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "책의 정보가 없습니다!", fontSize = 25.sp)
    }
}