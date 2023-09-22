package ggss.org.ggwander

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.annotation.SuppressLint
import androidx.activity.OnBackPressedCallback
import androidx.activity.ComponentActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        webView = findViewById(R.id.webview)
        webViewSetup()

        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    isEnabled = false
                    onBackPressed()
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup() {
        webView.webViewClient = WebViewClient()
        webView.apply {
            loadUrl("https://ggowtam.github.io/GG-wander/")
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }

    override fun onDestroy() {
        onBackPressedCallback.remove()
        super.onDestroy()
    }
}
