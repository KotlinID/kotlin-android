package com.baculsoft.kotlin.android.internal.injectors.component

import android.content.Context
import com.baculsoft.kotlin.android.internal.injectors.module.ActivityModule
import com.baculsoft.kotlin.android.internal.injectors.scope.ActivityContext
import com.baculsoft.kotlin.android.internal.injectors.scope.ActivityScope
import com.baculsoft.kotlin.android.views.main.MainActivity
import com.baculsoft.kotlin.android.views.result.ResultActivity
import dagger.Component

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@ActivityScope
@Component(
        dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(ActivityModule::class)
)
interface ActivityComponent {

    @ActivityContext
    fun getContext(): Context

    fun inject(mainActivity: MainActivity)

    fun inject(resultActivity: ResultActivity)
}