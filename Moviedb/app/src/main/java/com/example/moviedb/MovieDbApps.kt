package com.example.moviedb

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.multidex.MultiDex

class MovieDbApps: Application() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}