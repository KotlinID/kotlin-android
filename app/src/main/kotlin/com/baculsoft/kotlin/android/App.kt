package com.baculsoft.kotlin.android

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.baculsoft.kotlin.android.internal.injectors.component.ApplicationComponent
import com.baculsoft.kotlin.android.internal.injectors.component.DaggerApplicationComponent
import com.baculsoft.kotlin.android.internal.injectors.module.ApplicationModule
import com.baculsoft.kotlin.android.internal.injectors.module.NetworkModule
import com.baculsoft.kotlin.android.internal.injectors.module.UtilityModule

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
class App : Application() {

    companion object {
        lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this))
                                                        .networkModule(NetworkModule())
                                                        .utilityModule(UtilityModule())
                                                        .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}