package com.jamescoggan.sample.ui

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import com.jamescoggan.data.model.ResourceError
import com.jamescoggan.sample.R
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
            webViewClient =
                WebViewClient()  // This ensures the web content is loaded within the WebView
            loadData(encodedHtml.decodeFromBase64ToHtml(), "text/html", "UTF-8")
        }
    })
}

@OptIn(ExperimentalEncodingApi::class)
fun String.decodeFromBase64ToHtml(): String = String(Base64.decode(this))

fun ResourceError.toLocalizedString(context: Context): String = when (this) {
    is ResourceError.Fatal -> "${context.getString(R.string.resource_error_fatal)} ${this.throwable}"
    ResourceError.NoConnection -> context.getString(R.string.resource_error_no_connection)
    ResourceError.NotFound -> context.getString(R.string.resource_error_not_found)
    ResourceError.Timeout -> context.getString(R.string.resource_error_timed_out)
    ResourceError.Unauthorized -> context.getString(R.string.resource_error_unauthorised)
}