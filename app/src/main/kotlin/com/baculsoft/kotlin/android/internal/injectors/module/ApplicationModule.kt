package com.baculsoft.kotlin.android.internal.injectors.module

import android.app.Application
import android.content.Context
import com.baculsoft.kotlin.android.internal.injectors.scope.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Module
class ApplicationModule constructor(private val mApplication: Application) {

    @Provides
    internal fun provideApplication(): Application = mApplication

    @Provides
    @ApplicationContext
    internal fun provideApplicationContext(): Context = mApplication
}