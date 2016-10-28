package com.baculsoft.kotlin.android.utils

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
class Connections : Interceptor {

    companion object {
        @Volatile private var INSTANCE: Connections? = null

        @Synchronized fun get(): Connections {
            if (INSTANCE == null) {
                INSTANCE = Connections()
            }

            return INSTANCE as Connections
        }
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder().addHeader("Content-Type", "application/json")

        return chain.proceed(requestBuilder.build())
    }

    private val retrofit: Retrofit get() {
        val baseUrl = IConstants.IUrls.BASE_URL

        return Retrofit.Builder()
                       .client(okHttpClient)
                       .baseUrl(baseUrl)
                       .addConverterFactory(JacksonConverterFactory.create()).build()
    }

    private val okHttpClient: OkHttpClient get() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                           .connectTimeout(30, TimeUnit.SECONDS)
                           .writeTimeout(30, TimeUnit.SECONDS)
                           .readTimeout(30, TimeUnit.SECONDS)
                           .addInterceptor(this)
                           .addInterceptor(loggingInterceptor).build()
    }
}