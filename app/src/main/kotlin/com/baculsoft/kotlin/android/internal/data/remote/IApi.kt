package com.baculsoft.kotlin.android.internal.data.remote

import com.baculsoft.kotlin.android.utils.IConstants
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
interface IApi {

    @GET(IConstants.IUrls.IApis.TWITTER_SEARCH)
    fun getTwitterSearch(@Query("q") query: String,
                         @Query("t") typeSearch: String,
                         @Query("r") resultType: String,
                         @Query("n") maxId: Int,
                         @Query("k") key: String): Observable<TwitterSearchResponse>
}