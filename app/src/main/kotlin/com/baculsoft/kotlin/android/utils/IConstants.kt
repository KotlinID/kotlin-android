package com.baculsoft.kotlin.android.utils

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
interface IConstants {

    interface IKeys {
        companion object {
            const val API_KEY = "6d6bef05027caf28017ba74ebda3c6e0"
        }
    }

    interface IUrls {
        companion object {
            const val BASE_URL = "http://ibacor.com/api/"
            const val TWITTER_SEARCH = "twitter-search"
        }
    }

    interface IBundles {
        companion object {
            const val TEXT = "text"
        }
    }

    interface IPatterns {
        companion object {
            const val yyyyMMddHHmmss = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
            const val ddMMyyyyHHmm = "dd/MM/yyyy HH:mm"
        }
    }
}