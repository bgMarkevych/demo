package com.example.testdemo

import androidx.fragment.app.Fragment
import com.example.testdemo.fragments.SplashFragment
import com.example.testdemo.fragments.UIFragment
import com.example.testdemo.fragments.WebFragment

enum class AppState(val clazz: Class<*>) {
    Splash(SplashFragment::class.java),
    Web(WebFragment::class.java),
    Native(UIFragment::class.java);

}