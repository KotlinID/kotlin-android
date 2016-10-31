package com.baculsoft.kotlin.android.utils

import android.app.Activity
import android.content.Intent
import com.baculsoft.kotlin.android.views.activities.MainActivity
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

    fun openMainActivity(activity: Activity) {
        val intent: Intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    fun openResultActivity(activity: Activity, searchResult: String?) {
        val intent: Intent = Intent(activity, ResultActivity::class.java)
        intent.putExtra(IConstants.IBundles.SEARCH_RESULT, searchResult)
        activity.startActivity(intent)
        activity.finish()
    }
}