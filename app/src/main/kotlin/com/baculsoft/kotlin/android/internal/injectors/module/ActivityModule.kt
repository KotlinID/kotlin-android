package com.baculsoft.kotlin.android.internal.injectors.module

import android.app.Activity
import android.content.Context
import com.baculsoft.kotlin.android.internal.injectors.scope.ActivityContext
import dagger.Module
import dagger.Provides

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Module
class ActivityModule constructor(private val mActivity: Activity) {

    @Provides
    internal fun provideActivity(): Activity = mActivity

    @Provides
    @ActivityContext
    internal fun provideActivityContext(): Context = mActivity
}