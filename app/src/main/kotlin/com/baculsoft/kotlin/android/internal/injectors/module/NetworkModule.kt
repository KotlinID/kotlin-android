package com.baculsoft.kotlin.android.internal.injectors.module

import com.baculsoft.kotlin.android.internal.data.remote.IApi
import com.baculsoft.kotlin.android.utils.IConstants
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideApi(retrofit: Retrofit): IApi = retrofit.create(IApi::class.java)

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val baseUrl: String = IConstants.IUrls.BASE_URL

        return Retrofit.Builder().client(okHttpClient)
                                 .baseUrl(baseUrl)
                                 .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                 .addConverterFactory(JacksonConverterFactory.create())
                                 .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val interceptor: Interceptor = Interceptor { chain ->
            val request: Request = chain.request()
            val builder: Request.Builder = request.newBuilder().addHeader("Content-Type", "application/json")

            chain.proceed(builder.build())
        }

        val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                                     .readTimeout(15, TimeUnit.SECONDS)
                                     .writeTimeout(15, TimeUnit.SECONDS)
                                     .retryOnConnectionFailure(true)
                                     .addInterceptor(interceptor)
                                     .addInterceptor(httpLoggingInterceptor)
                                     .build()
    }
}