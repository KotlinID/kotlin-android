package com.baculsoft.kotlin.android.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.baculsoft.kotlin.android.R
import com.baculsoft.kotlin.android.internal.api.response.TwitterSearchResponse
import com.baculsoft.kotlin.android.utils.Connections
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        getTwitterSearch()
    }

    private fun setToolbar() {
        toolbar_main.setTitle(R.string.app_name)
        toolbar_main.setSubtitle(R.string.app_desc)
        setSupportActionBar(toolbar_main)
    }

    private fun getTwitterSearch() {
        Connections.get().api().getTwitterSearch("SumpahPemuda", "tag", "recent", "6d6bef05027caf28017ba74ebda3c6e0").enqueue(object : Callback<TwitterSearchResponse> {
            override fun onResponse(response: Call<TwitterSearchResponse>?, responses: Response<TwitterSearchResponse>?) {
                val statuses: List<TwitterSearchResponse.Statuses>? = responses!!.body().statuses
                val text: String? = statuses!![0].text
                Log.d("SUCCESS", text)
            }

            override fun onFailure(response: Call<TwitterSearchResponse>?, throwable: Throwable?) {
                // TODO Not yet implemented
            }
        })
    }
}