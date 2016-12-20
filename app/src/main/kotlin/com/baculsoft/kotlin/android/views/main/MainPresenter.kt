package com.baculsoft.kotlin.android.views.main

import android.text.TextUtils
import android.widget.Button
import com.baculsoft.kotlin.android.extensions.safeUnsubscribe
import com.baculsoft.kotlin.android.internal.data.local.TwitterSearch
import com.baculsoft.kotlin.android.internal.data.local.TwitterSearchResult
import com.baculsoft.kotlin.android.internal.data.remote.IApi
import com.baculsoft.kotlin.android.internal.data.remote.TwitterSearchResponse
import com.baculsoft.kotlin.android.utils.IConstants
import com.baculsoft.kotlin.android.views.base.IPresenter
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
class MainPresenter @Inject constructor() : IPresenter<MainView> {
    private var mView: MainView? = null
    private var mSubscription: Subscription? = null

    @Inject
    lateinit var api: IApi

    override fun onAttach(view: MainView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
        mSubscription.safeUnsubscribe()
    }

    fun validateSearch(query: String, page: String, button: Button) {
        if (!TextUtils.isEmpty(query) && !TextUtils.isEmpty(page)) {
            button.isEnabled = true
        } else {
            button.isEnabled = false
        }
    }

    fun getTwitterSearch(query: String, page: String, searchType: String, resultType: String) {
        val key: String = IConstants.IKeys.API_KEY

        var maxId: Int = 0
        if (!TextUtils.isEmpty(page)) {
            maxId = Integer.parseInt(page)
        }

        mView?.onShowProgressDialog()
        mSubscription.safeUnsubscribe()
        mSubscription = api.getTwitterSearch(query, searchType, resultType, maxId, key).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(object : Subscriber<TwitterSearchResponse>() {
            override fun onCompleted() {
                mView?.onDismissProgressDialog()
            }

            override fun onError(throwable: Throwable?) {
                mView?.onDismissProgressDialog()
                mView?.onConnectionError()
            }

            override fun onNext(twitterSearchResponse: TwitterSearchResponse?) {
                val statuses: List<TwitterSearchResponse.Statuses>? = twitterSearchResponse?.statuses
                if (statuses?.size != 0) {
                    val searchMetadata: TwitterSearchResponse.SearchMetadata? = twitterSearchResponse?.searchMetadata
                    val results: List<TwitterSearchResult>? = getTwitterSearchResult(statuses)
                    val count: Int? = searchMetadata?.count

                    val twitterSearch: TwitterSearch = TwitterSearch(results as List<TwitterSearchResult>, count as Int)
                    mView?.onNavigateView(twitterSearch)
                } else {
                    mView?.onShowError()
                }
            }
        })
    }

    private fun getTwitterSearchResult(statuses: List<TwitterSearchResponse.Statuses>?): List<TwitterSearchResult>? {
        val results: List<TwitterSearchResult>? = statuses?.map {
            val text: String = it.text
            val createdAt: String = it.createdAt

            TwitterSearchResult(text, createdAt)
        }

        return results
    }
}