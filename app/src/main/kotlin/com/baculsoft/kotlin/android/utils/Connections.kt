package com.baculsoft.kotlin.android.utils

import com.baculsoft.kotlin.android.internal.api.IApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
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
        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder().addHeader("Content-Type", "application/json")

        return chain.proceed(requestBuilder.build())
    }

    fun api(): IApi {
        return retrofit().create(IApi::class.java)
    }

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
                       .client(okHttpClient())
                       .baseUrl(IConstants.IUrls.BASE_URL)
                       .addConverterFactory(JacksonConverterFactory.create()).build()
    }

    private fun okHttpClient(): OkHttpClient {
        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                           .connectTimeout(30, TimeUnit.SECONDS)
                           .writeTimeout(15, TimeUnit.SECONDS)
                           .readTimeout(15, TimeUnit.SECONDS)
                           .addInterceptor(this)
                           .addInterceptor(loggingInterceptor).build()
    }
}