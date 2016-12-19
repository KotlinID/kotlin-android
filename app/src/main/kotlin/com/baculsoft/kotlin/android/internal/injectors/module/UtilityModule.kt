package com.baculsoft.kotlin.android.internal.injectors.module

import com.baculsoft.kotlin.android.utils.Commons
import com.baculsoft.kotlin.android.utils.Dates
import com.baculsoft.kotlin.android.utils.Keyboards
import com.baculsoft.kotlin.android.utils.Navigators
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Module
class UtilityModule {

    @Provides
    @Singleton
    internal fun provideCommonUtils(): Commons = Commons()

    @Provides
    @Singleton
    internal fun provideDates(): Dates = Dates()

    @Provides
    @Singleton
    internal fun provideKeyboards(): Keyboards = Keyboards()

    @Provides
    @Singleton
    internal fun provideNavigators(): Navigators = Navigators()
}