package com.zzn.wanandroid.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_home_web_view.*
import android.webkit.WebViewClient
import android.webkit.WebSettings
import android.webkit.WebView


class HomeWebView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.zzn.wanandroid.R.layout.activity_home_web_view)
        val titles = intent.getStringExtra("title")
        val url = intent.getStringExtra("link")

        title_tv.text = titles

        back_rl.setOnClickListener {
            finish()
        }


        InitWebView(url)


    }

    private fun InitWebView(url: String) {
        val webSettings=webView.settings
        webSettings.javaScriptCanOpenWindowsAutomatically=true
        webSettings.javaScriptEnabled=true
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.allowFileAccess = true// 设置允许访问文件数据
        webSettings.setSupportZoom(true)//支持缩放
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webSettings.domStorageEnabled = true
        webSettings.databaseEnabled = true
        webView!!.setWebViewClient(webClient)
        webView!!.setOnKeyListener(OnKeyEvent)
        webView.loadUrl(url)


    }
    private val OnKeyEvent = View.OnKeyListener { v, keyCode, event ->
        val action = event.action
        val webView = v as WebView
        if (KeyEvent.ACTION_DOWN == action && KeyEvent.KEYCODE_BACK == keyCode) {
            if (webView?.canGoBack()) {
                webView.goBack()
                return@OnKeyListener true
            }
        }
        false
    }
    private val webClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

            return false
        }
    }

}
