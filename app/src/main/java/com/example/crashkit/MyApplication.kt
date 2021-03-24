package com.example.crashkit

import android.app.Application
import com.crash.kit.CrashMgr

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        CrashMgr.init()
    }
}