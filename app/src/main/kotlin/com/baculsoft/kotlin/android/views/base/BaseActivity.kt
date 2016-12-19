package com.baculsoft.kotlin.android.views.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
abstract class BaseActivity : AppCompatActivity() {
    protected abstract fun getContentView(): Int

    protected abstract fun initComponents(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        initComponents(savedInstanceState)
    }

    protected fun getStatusBarHeight(): Int? {
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")

        var result: Int? = 0
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }

        return result
    }
}