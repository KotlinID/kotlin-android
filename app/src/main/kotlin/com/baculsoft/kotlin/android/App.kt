package com.baculsoft.kotlin.android

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

/**
 * @author Budi Oktaviyan Suryanto (budi@setipe.com)
 */
open class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}