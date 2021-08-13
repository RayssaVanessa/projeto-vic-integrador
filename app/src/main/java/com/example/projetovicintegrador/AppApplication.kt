package com.example.projetovicintegrador

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : Application() {
    companion object {
        lateinit var staticContex: Context
    }

    override fun onCreate() {
        super.onCreate()
        staticContex = this.applicationContext
        setupCrashlytics()
    }
    private fun setupCrashlytics(){
        FirebaseApp.initializeApp(this)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }
}