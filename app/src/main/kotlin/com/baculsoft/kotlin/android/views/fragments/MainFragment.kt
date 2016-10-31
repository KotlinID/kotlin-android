package com.baculsoft.kotlin.android.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.baculsoft.kotlin.android.R
import com.baculsoft.kotlin.android.internal.api.response.TwitterSearchResponse
import com.baculsoft.kotlin.android.utils.Connections
import com.baculsoft.kotlin.android.utils.IConstants
import com.baculsoft.kotlin.android.utils.Keyboards
import com.baculsoft.kotlin.android.utils.Navigators
import com.baculsoft.kotlin.android.views.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
class MainFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setComponent()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater?.inflate(R.layout.fragment_main, container, false) as View
        return view
    }

    fun getActionBar(): ActionBar? {
        return (activity as AppCompatActivity).supportActionBar
    }

    private fun setComponent() {
        getActionBar()?.title = activity.resources.getString(R.string.app_name)
        getActionBar()?.subtitle = activity.resources.getString(R.string.app_desc)

        tiet_main_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onValidate()
            }
        })
    }

    private fun onValidate() {
        if (!TextUtils.isEmpty(tiet_main_search.text)) {
            btn_main_search.isEnabled = true
            btn_main_search.setOnClickListener { view -> onButtonClick() }
        } else {
            btn_main_search.isEnabled = false
        }
    }

    private fun onButtonClick() {
        sv_main_search.visibility = View.GONE
        btn_main_search.visibility = View.GONE
        pb_main_search.visibility = View.VISIBLE

        Keyboards.get().hide(rl_main_search, context)
        getTwitterSearch()
    }

    private fun getTwitterSearch() {
        val searchText: String = tiet_main_search.text.toString()
        val searchType: String = sp_main_search_type.selectedItem.toString().toLowerCase()
        val searchResult: String = sp_main_search_result.selectedItem.toString().toLowerCase()

        Connections.get().api().getTwitterSearch(searchText, searchType, searchResult, IConstants.IKeys.API_KEY).enqueue(object : Callback<TwitterSearchResponse> {
            override fun onResponse(call: Call<TwitterSearchResponse>?, response: Response<TwitterSearchResponse>?) {
                when (response?.code()) {
                    200 -> {
                        val searchResult = response?.body()?.statuses?.get(0)?.text
                        Navigators.get().openResultActivity(activity, searchResult)
                    }
                }
            }

            override fun onFailure(call: Call<TwitterSearchResponse>?, throwable: Throwable?) {
                Toast.makeText(context, "onFailure -> " + throwable?.message, Toast.LENGTH_SHORT).show()
                Log.e(MainActivity::class.java.simpleName, throwable?.message)
            }
        })
    }
}