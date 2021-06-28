package com.example.moviedb.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.moviedb.abstraction.activity.BaseActivity
import com.example.moviedb.databinding.LayoutSplashActivityBinding
import com.example.moviedb.utils.UserSession

class SplashActivity:BaseActivity<LayoutSplashActivityBinding>() {
    override fun getViewBinding() = LayoutSplashActivityBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userSession = UserSession(this)
        Handler(Looper.getMainLooper()).postDelayed({
            openActivity(HomeActivity::class.java,false)
        }, 2000)
    }



}