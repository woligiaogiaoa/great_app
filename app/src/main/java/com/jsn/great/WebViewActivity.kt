package com.jsn.great

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_web.*

class WebViewActivity : Activity() {

    companion object{

        const val KEY_URL="url"
        fun start(activity:Activity,url:String){
            Intent(activity,WebViewActivity::class.java).apply {
                putExtra(KEY_URL,url)
            }.also {
                activity.startActivity(it)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        intent.getStringExtra(KEY_URL)?.also {
            webView.loadUrl(it)
        }?: finish()
    }
}

fun String.isNullOrEmpty()=TextUtils.isEmpty(this)