package com.baculsoft.kotlin.android.utils

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
interface IConstants {

    interface IKeys {
        companion object {
            val API_KEY = "6d6bef05027caf28017ba74ebda3c6e0"
        }
    }

    interface IUrls {
        companion object {
            val BASE_URL = "http://ibacor.com/api/"
            val TWITTER_SEARCH = "twitter-search"
        }
    }

    interface IPatterns {
        companion object {
            val yyyyMMddHHmmss = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
            val ddMMyyyyHHmm = "dd/MM/yyyy HH:mm"
        }
    }
}