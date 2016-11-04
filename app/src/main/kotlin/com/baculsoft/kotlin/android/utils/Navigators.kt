package com.baculsoft.kotlin.android.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.baculsoft.kotlin.android.internal.pojo.TwitterSearch
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

    fun openResultActivity(context: Context, twitterSearch: TwitterSearch) {
        val intent: Intent = Intent(context, ResultActivity::class.java)
        val bundle: Bundle = Bundle()
        bundle.putParcelable(IConstants.IBundles.TWITTER_SEARCH, twitterSearch)
        intent.putExtras(bundle)
        context.startActivity(intent)
    }
}