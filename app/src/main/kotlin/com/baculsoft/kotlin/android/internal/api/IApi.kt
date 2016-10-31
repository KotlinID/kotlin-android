package com.baculsoft.kotlin.android.internal.api

import com.baculsoft.kotlin.android.internal.api.response.TwitterSearchResponse
import com.baculsoft.kotlin.android.utils.IConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
interface IApi {

    @GET(IConstants.IUrls.TWITTER_SEARCH)
    fun getTwitterSearch(@Query("q") q: String,
                         @Query("t") t: String,
                         @Query("r") r: String,
                         @Query("k") k: String): Call<TwitterSearchResponse>
}