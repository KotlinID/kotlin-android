package com.baculsoft.kotlin.android.views.fragments

import android.app.ProgressDialog
import android.content.Context
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
import android.view.inputmethod.EditorInfo
import com.baculsoft.kotlin.android.R
import com.baculsoft.kotlin.android.internal.api.response.TwitterSearchResponse
import com.baculsoft.kotlin.android.internal.pojo.TwitterSearch
import com.baculsoft.kotlin.android.internal.pojo.TwitterSearchResult
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
    private var progressDialog: ProgressDialog? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setComponent()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater?.inflate(R.layout.fragment_main, container, false) as View
        return view
    }

    fun getActionBar(): ActionBar? {
        return (context as AppCompatActivity).supportActionBar
    }

    fun getProgressDialog(context: Context): ProgressDialog? {
        val progressDialog: ProgressDialog = ProgressDialog(context)
        progressDialog.setMessage(context.resources.getString(R.string.message_search))
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.setOnKeyListener { dialogInterface, keyCode, event -> true }

        return progressDialog
    }

    private fun setComponent() {
        getActionBar()?.title = context.resources.getString(R.string.app_name)
        getActionBar()?.subtitle = context.resources.getString(R.string.app_desc)
        progressDialog = getProgressDialog(context)

        tiet_main.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onValidate()
            }
        })

        tiet_main.setOnEditorActionListener { textView, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    if (btn_main.isEnabled) {
                        onButtonClick()
                        true
                    } else {
                        false
                    }
                }
                else -> false
            }
        }
    }

    private fun onValidate() {
        if (!TextUtils.isEmpty(tiet_main.text)) {
            btn_main.isEnabled = true
            btn_main.setOnClickListener { view -> onButtonClick() }
        } else {
            btn_main.isEnabled = false
        }
    }

    private fun onButtonClick() {
        Keyboards.get().hide(rl_main, context)
        progressDialog?.show()
        getTwitterSearch()
    }

    private fun getTwitterSearch() {
        val query: String = tiet_main.text.toString()
        val typeSearch: String = sp_main_type.selectedItem.toString().toLowerCase()
        val resultType: String = sp_main_result.selectedItem.toString().toLowerCase()
        val key: String = IConstants.IKeys.API_KEY

        Connections.get().api().getTwitterSearch(query, typeSearch, resultType, key).enqueue(object : Callback<TwitterSearchResponse> {
            override fun onResponse(call: Call<TwitterSearchResponse>?, response: Response<TwitterSearchResponse>?) {
                when (response?.code()) {
                    200 -> {
                        onResetField()
                        onSearchResult(response?.body())
                    }
                    else -> {
                        onResetField()
                        Snackbar.make(rl_main, "onUnknownResponse -> ".plus(response?.code()), Snackbar.LENGTH_SHORT).show()
                        Log.e(MainFragment::class.java.simpleName, "onUnknownResponse -> ".plus(response?.code()))
                    }
                }
            }

            override fun onFailure(call: Call<TwitterSearchResponse>?, throwable: Throwable?) {
                onResetField()
                Snackbar.make(rl_main, "onFailure -> ".plus(throwable?.message), Snackbar.LENGTH_SHORT).show()
                Log.e(MainFragment::class.java.simpleName, "onFailure -> ".plus(throwable?.message))
            }
        })
    }

    private fun onResetField() {
        progressDialog?.dismiss()
        tiet_main.text.clear()
        sp_main_type.setSelection(0)
        sp_main_result.setSelection(0)
    }

    private fun onSearchResult(response: TwitterSearchResponse?) {
        val statuses: List<TwitterSearchResponse.Statuses>? = response?.statuses

        if (statuses?.size != 0) {
            val results: List<TwitterSearchResult> = statuses!!.map {
                val result = it.text
                TwitterSearchResult(result)
            }

            val twitterSearch: TwitterSearch = TwitterSearch(results)
            Navigators.get().openResultActivity(context, twitterSearch)
        } else {
            Snackbar.make(rl_main, "onSearchResult -> ".plus(statuses?.size), Snackbar.LENGTH_SHORT).show()
            Log.e(MainFragment::class.java.simpleName, "onSearchResult -> ".plus(statuses?.size))
        }
    }
}