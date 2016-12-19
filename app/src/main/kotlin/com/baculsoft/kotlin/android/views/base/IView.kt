package com.baculsoft.kotlin.android.views.base

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
interface IView {
    fun inject()

    fun onAttach()

    fun onDetach()
}