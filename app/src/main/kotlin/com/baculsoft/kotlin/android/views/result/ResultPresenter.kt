package com.baculsoft.kotlin.android.views.result

import android.app.Activity
import com.baculsoft.kotlin.android.internal.data.local.TwitterSearch
import com.baculsoft.kotlin.android.internal.data.local.TwitterSearchResult
import com.baculsoft.kotlin.android.utils.IConstants
import com.baculsoft.kotlin.android.views.base.IPresenter
import org.parceler.Parcels
import javax.inject.Inject

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
class ResultPresenter @Inject constructor() : IPresenter<ResultView> {
    private var mView: ResultView? = null

    override fun onAttach(view: ResultView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun getResults(activity: Activity): List<TwitterSearchResult> = getTwitterSearch(activity).twitterSearchResults

    fun getCount(activity: Activity): Int = getTwitterSearch(activity).count

    private fun getTwitterSearch(activity: Activity): TwitterSearch = Parcels.unwrap(activity.intent.getParcelableExtra(IConstants.IBundles.TWITTER_SEARCH))
}