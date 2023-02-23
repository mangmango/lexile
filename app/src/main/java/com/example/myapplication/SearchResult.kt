package com.example.myapplication

import androidx.compose.runtime.Composable
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SearchResult {
    companion object {
        @Composable
        fun SearchResult(q: String?) {
            if (q != null) {
                getBooks(q).forEach { element ->
                    element.book()
                }
            }
        }

        private fun getBooks(bookName: String): ArrayList<Book> {
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
            val items = json.getJSONArray("items")
            val books = ArrayList<Book>()

            for (i in 1..items.length()) {
                val bookInfo = items.getJSONObject(i)
                val volumeInfo = bookInfo.getJSONObject("volumeInfo")
                val title = volumeInfo.getString("title")
                val authors = volumeInfo.getJSONArray("authors")
                val description = volumeInfo.getString("description")
                val categories = volumeInfo.getJSONArray("categories")
                val saleInfo = bookInfo.getJSONObject("saleInfo")
                val pageCount = volumeInfo.getInt("pageCount")
                val book = Book(title, authors, description, categories, saleInfo, pageCount)

                books.add(book)
            }

            return books
        }
    }
}