package com.example.testdemo.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.testdemo.BuildConfig
import com.example.testdemo.R

class WebFragment : Fragment(R.layout.fragment_web) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<WebView>(R.id.browser).let {
            it.loadUrl(BuildConfig.weblink)
        }
    }


}