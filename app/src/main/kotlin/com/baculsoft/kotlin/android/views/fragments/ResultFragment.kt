package com.baculsoft.kotlin.android.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baculsoft.kotlin.android.R
import com.baculsoft.kotlin.android.internal.pojo.TwitterSearch
import com.baculsoft.kotlin.android.internal.pojo.TwitterSearchResult
import com.baculsoft.kotlin.android.utils.IConstants
import com.baculsoft.kotlin.android.views.adapters.ResultAdapter
import kotlinx.android.synthetic.main.fragment_result.*

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
class ResultFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setComponent()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater?.inflate(R.layout.fragment_result, container, false) as View
        return view
    }

    fun getActionBar(): ActionBar? {
        return (context as AppCompatActivity).supportActionBar
    }

    private fun setComponent() {
        val twitterSearch: TwitterSearch = activity.intent.getParcelableExtra(IConstants.IBundles.TWITTER_SEARCH)
        val results: List<TwitterSearchResult> = twitterSearch.results
        val count: Int = twitterSearch.count

        getActionBar()?.title = context.resources.getString(R.string.text_search_result)
        getActionBar()?.subtitle = String.format(context.resources.getString(R.string.text_search_count), count)

        setAdapter(results)
    }

    private fun setAdapter(results: List<TwitterSearchResult>) {
        val adapter: ResultAdapter = ResultAdapter(results)
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)

        rv_result.layoutManager = linearLayoutManager
        rv_result.smoothScrollToPosition(rv_result.bottom)
        rv_result.adapter = adapter
    }
}