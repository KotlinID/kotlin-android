package com.baculsoft.kotlin.android.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
class Keyboards {
    fun show(view: View, context: Context) {
        val inputMethodManager: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInputFromInputMethod(view.windowToken, 0)
    }

    fun hide(view: View, context: Context) {
        val inputMethodManager: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun toggle(context: Context) {
        val inputMethodManager: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun initKeyboardFocus(activity: Activity, view: View, mode: Int) {
        view.requestFocus()
        activity.window.setSoftInputMode(mode)
    }
}