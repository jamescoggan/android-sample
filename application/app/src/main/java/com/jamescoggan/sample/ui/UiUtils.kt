package com.jamescoggan.sample.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.CoroutineStart
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Composable
fun isPortraitMode(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT
}

@Composable
fun Base64HtmlView(encodedHtml: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()  // This ensures the web content is loaded within the WebView
            loadData(encodedHtml.decodeFromBase64ToHtml(), "text/html", "UTF-8")
        }
    })
}

@OptIn(ExperimentalEncodingApi::class)
fun String.decodeFromBase64ToHtml(): String = String(Base64.decode(this))