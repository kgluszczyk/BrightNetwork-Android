package com.brightnetwork.summerfests

import android.app.Application
import android.util.Log
import androidx.room.Room

class App : Application() {

    companion object {
      lateinit var database: AppDatabase
    }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "festivals"
        ).fallbackToDestructiveMigration()
            .build()
        Log.d("LIFECYCLE", "APP-starts")
    }
}