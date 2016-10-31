package com.baculsoft.kotlin.android.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baculsoft.kotlin.android.R
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
        return (activity as AppCompatActivity).supportActionBar
    }

    private fun setComponent() {
        getActionBar()?.title = activity.resources.getString(R.string.text_result)

        val searchResult: String = activity.intent.getStringExtra(IConstants.IBundles.SEARCH_RESULT)
        tv_result.text = searchResult
    }
}