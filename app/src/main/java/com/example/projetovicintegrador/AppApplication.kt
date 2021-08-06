package com.example.projetovicintegrador

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : Application() {
    companion object {
        lateinit var staticContex: Context
    }

    override fun onCreate() {
        super.onCreate()
        staticContex = this.applicationContext
    }
}