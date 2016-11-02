package com.baculsoft.kotlin.android.utils

import android.content.Context
import android.content.Intent
import com.baculsoft.kotlin.android.views.activities.ResultActivity

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
class Navigators {

    companion object {
        @Volatile private var INSTANCE: Navigators? = null

        @Synchronized fun get(): Navigators {
            if (INSTANCE == null) {
                INSTANCE = Navigators()
            }

            return INSTANCE as Navigators
        }
    }

    fun openResultActivity(context: Context, text: String?) {
        val intent: Intent = Intent(context, ResultActivity::class.java)
        intent.putExtra(IConstants.IBundles.TEXT, text)
        context.startActivity(intent)
    }
}