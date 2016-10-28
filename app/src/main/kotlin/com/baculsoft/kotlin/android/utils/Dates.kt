package com.baculsoft.kotlin.android.utils

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

        private val yyyyMMdd = SimpleDateFormat(IConstants.IPatterns.yyyyMMdd, Locale.getDefault())
        private val yyyyMMddHHmmss = SimpleDateFormat(IConstants.IPatterns.yyyyMMddHHmmss, Locale.getDefault())
    }

    fun getDate(pDate: String): Date {
        val dateFormat = yyyyMMddHHmmss
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date: Date

        try {
            date = dateFormat.parse(pDate)
        } catch (e: ParseException) {
            throw RuntimeException(e)
        }

        return date
    }

    fun getDate(date: Date): String {
        return yyyyMMdd.format(date)
    }

    /**
     * @return Current Year
     */
    val currentYear: Int get() = Calendar.getInstance().get(Calendar.YEAR)

    /**
     * @return Current Month
     */
    val currentMonth: Int get() = Calendar.getInstance().get(Calendar.MONTH)

    /**
     * @return Current Day
     */
    val currentDay: Int get() = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
}