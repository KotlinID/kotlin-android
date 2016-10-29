package com.baculsoft.kotlin.android.internal.api

import com.baculsoft.kotlin.android.internal.api.response.TwitterSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Budi Oktaviyan Suryanto (budi@setipe.com)
 */
interface IApi {

    @GET("twitter-search")
    fun getTwitterSearch(@Query("q") q: String,
                         @Query("t") t: String,
                         @Query("r") r: String,
                         @Query("k") k: String): Call<TwitterSearchResponse>
}