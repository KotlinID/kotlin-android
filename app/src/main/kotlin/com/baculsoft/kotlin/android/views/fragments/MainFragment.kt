package com.baculsoft.kotlin.android.views.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
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
import com.baculsoft.kotlin.android.R
import com.baculsoft.kotlin.android.internal.api.response.TwitterSearchResponse
import com.baculsoft.kotlin.android.utils.Connections
import com.baculsoft.kotlin.android.utils.IConstants
import com.baculsoft.kotlin.android.utils.Keyboards
import com.baculsoft.kotlin.android.utils.Navigators
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
        hideField()
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
                        resetField()

                        val statuses: List<TwitterSearchResponse.Statuses>? = response?.body()?.statuses
                        if (statuses?.size != 0) {
                            val text: String? = statuses?.get(0)?.text
                            Navigators.get().openResultActivity(activity, text)
                        } else {
                            Snackbar.make(rl_main_search, "No result! -> " + statuses?.size, Snackbar.LENGTH_SHORT).show()
                            Log.e(MainFragment::class.java.simpleName, "No result! -> " + statuses?.size)
                        }
                    }
                    else -> {
                        resetField()
                        Snackbar.make(rl_main_search, "onUnknownResponse -> " + response?.code(), Snackbar.LENGTH_SHORT).show()
                        Log.e(MainFragment::class.java.simpleName, "onUnknownResponse -> " + response?.code())
                    }
                }
            }

            override fun onFailure(call: Call<TwitterSearchResponse>?, throwable: Throwable?) {
                resetField()
                Snackbar.make(rl_main_search, "onFailure -> " + throwable?.message, Snackbar.LENGTH_SHORT).show()
                Log.e(MainFragment::class.java.simpleName, "onFailure -> " + throwable?.message)
            }
        })
    }

    private fun hideField() {
        sv_main_search.visibility = View.GONE
        btn_main_search.visibility = View.GONE
        pb_main_search.visibility = View.VISIBLE

        Keyboards.get().hide(rl_main_search, context)
    }

    private fun resetField() {
        tiet_main_search.text.clear()
        sp_main_search_type.setSelection(0)
        sp_main_search_result.setSelection(0)

        pb_main_search.visibility = View.GONE
        sv_main_search.visibility = View.VISIBLE
        btn_main_search.visibility = View.VISIBLE
    }
}