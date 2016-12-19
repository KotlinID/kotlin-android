package com.baculsoft.kotlin.android.utils

import android.app.ProgressDialog
import android.content.Context
import android.support.design.widget.Snackbar
import android.view.ViewGroup

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
class Commons {
    fun showGeneralError(view: ViewGroup, message: String): Snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)

    fun getProgressDialog(context: Context): ProgressDialog {
        val progressDialog: ProgressDialog = ProgressDialog(context)
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.setOnKeyListener { dialog, keyCode, event -> true }

        return progressDialog
    }
}