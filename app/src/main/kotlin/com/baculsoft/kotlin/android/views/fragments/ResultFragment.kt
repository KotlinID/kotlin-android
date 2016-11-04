package com.baculsoft.kotlin.android.views.fragments

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baculsoft.kotlin.android.R
import com.baculsoft.kotlin.android.internal.pojo.TwitterSearch
import com.baculsoft.kotlin.android.internal.pojo.TwitterSearchResult
import com.baculsoft.kotlin.android.utils.IConstants
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
        getActionBar()?.title = context.resources.getString(R.string.text_search_result)

        val twitterSearch: TwitterSearch = activity.intent.getParcelableExtra(IConstants.IBundles.TWITTER_SEARCH)
        val results: List<TwitterSearchResult> = twitterSearch.results
        val text: String = results[0].text
        tv_result.text = convertToHtml(text)
    }

    private fun convertToHtml(source: String?): CharSequence? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            return Html.fromHtml(source)
        }
    }
}