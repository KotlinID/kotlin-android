package com.baculsoft.kotlin.android.views.base

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
interface IPresenter<in T : IView> {
    fun onAttach(view: T)

    fun onDetach()
}