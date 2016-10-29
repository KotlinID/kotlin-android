package com.baculsoft.kotlin.android.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
class Dates {

    companion object {
        @Volatile private var INSTANCE: Dates? = null

        @Synchronized fun get(): Dates {
            if (INSTANCE == null) {
                INSTANCE = Dates()
            }

            return INSTANCE as Dates
        }

        private val yyyyMMddHHmmss: SimpleDateFormat = SimpleDateFormat(IConstants.IPatterns.yyyyMMddHHmmss, Locale.getDefault())
        private val ddMMyyyyHHmm: SimpleDateFormat = SimpleDateFormat(IConstants.IPatterns.ddMMyyyyHHmm, Locale.getDefault())
    }

    fun getDateTime(dateTime: String): Date {
        val dateFormat: DateFormat = yyyyMMddHHmmss
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        val date: Date
        try {
            date = dateFormat.parse(dateTime)
        } catch (e: ParseException) {
            throw RuntimeException(e)
        }

        return date
    }

    fun getDateTime(dateTime: Date): String {
        return ddMMyyyyHHmm.format(dateTime)
    }
}