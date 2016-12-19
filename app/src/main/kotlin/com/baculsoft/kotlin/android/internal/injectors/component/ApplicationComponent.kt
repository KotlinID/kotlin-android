package com.baculsoft.kotlin.android.internal.injectors.component

import android.app.Application
import android.content.Context
import com.baculsoft.kotlin.android.internal.data.remote.IApi
import com.baculsoft.kotlin.android.internal.injectors.module.ApplicationModule
import com.baculsoft.kotlin.android.internal.injectors.module.NetworkModule
import com.baculsoft.kotlin.android.internal.injectors.module.UtilityModule
import com.baculsoft.kotlin.android.internal.injectors.scope.ApplicationContext
import com.baculsoft.kotlin.android.utils.Commons
import com.baculsoft.kotlin.android.utils.Dates
import com.baculsoft.kotlin.android.utils.Keyboards
import com.baculsoft.kotlin.android.utils.Navigators
import dagger.Component
import javax.inject.Singleton

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        NetworkModule::class,
        UtilityModule::class
))
interface ApplicationComponent {

    @ApplicationContext
    fun getContext(): Context

    fun inject(application: Application)

    // Network Module
    fun getApi(): IApi

    // Utility Module
    fun getCommonUtils(): Commons

    fun getDates(): Dates

    fun getKeyboards(): Keyboards

    fun getNavigators(): Navigators
}