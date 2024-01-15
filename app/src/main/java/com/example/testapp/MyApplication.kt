package com.example.testapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import java.util.Calendar.DATE

class MyApplication: Application() {

    data class User(val name:String, val pwd:String)

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        var user: User? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        DATE
    }
}