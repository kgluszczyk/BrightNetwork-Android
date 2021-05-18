package com.brightnetwork.summerfests

import android.app.Application
import android.util.Log

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("LIFECYCLE", "APP-starts")
    }
}