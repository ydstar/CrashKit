package com.example.crashkit

import android.app.Application
import com.crash.kit.CrashKitManager

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        CrashKitManager.init()
    }
}