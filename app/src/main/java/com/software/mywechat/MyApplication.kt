package com.software.mywechat

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        const val TAG = "congcong"

        lateinit var instance: MyApplication
    }
}