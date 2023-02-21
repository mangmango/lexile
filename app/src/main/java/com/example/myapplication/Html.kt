package com.example.myapplication

import java.net.URL
// <html><head></head><body></body></html>

class Html(url: URL) {
    val url = URL("https://www.google.com")
    val connection = url.openConnection()


}