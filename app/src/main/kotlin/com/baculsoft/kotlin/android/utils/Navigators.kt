package com.baculsoft.kotlin.android.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.baculsoft.kotlin.android.internal.data.local.TwitterSearch
import com.baculsoft.kotlin.android.views.result.ResultActivity
import org.parceler.Parcels

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
class Navigators {
    private val TAG: String = Navigators::class.java.simpleName

    fun openResultActivity(activity: Activity, twitterSearch: TwitterSearch) {
        try {
            val intent: Intent = Intent(activity, ResultActivity::class.java)
            val bundle: Bundle = Bundle()
            bundle.putParcelable(IConstants.IBundles.TWITTER_SEARCH, Parcels.wrap(twitterSearch))
            intent.putExtras(bundle)
            activity.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }
    }
}