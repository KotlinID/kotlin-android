package com.baculsoft.kotlin.android.views.main

import com.baculsoft.kotlin.android.internal.data.local.TwitterSearch
import com.baculsoft.kotlin.android.views.base.IView

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
interface MainView : IView {
    fun onValidate()

    fun onShowProgressDialog()

    fun onDismissProgressDialog()

    fun onShowError()

    fun onConnectionError()

    fun onNavigateView(twitterSearch: TwitterSearch)
}