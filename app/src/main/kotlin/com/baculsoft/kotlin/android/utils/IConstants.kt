package com.baculsoft.kotlin.android.utils

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
interface IConstants {

    interface IUrls {
        companion object {
            val BASE_URL = "http://ibacor.com/api/"
        }
    }

    interface IPatterns {
        companion object {
            val yyyyMMdd = "yyyy-MM-dd"
            val yyyyMMddHHmmss = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        }
    }
}