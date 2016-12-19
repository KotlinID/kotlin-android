package com.baculsoft.kotlin.android.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
class Dates {
    private val EEEMMMddHHmmsszzzyyyy: SimpleDateFormat = SimpleDateFormat(IConstants.IPatterns.EEEMMMddHHmmsszzzyyyy, Locale.getDefault())
    private val ddMMyyyyHHmmss: SimpleDateFormat = SimpleDateFormat(IConstants.IPatterns.ddMMyyyyHHmmss, Locale.getDefault())

    fun getDateTime(dateTime: String): Date {
        val dateFormat: DateFormat = EEEMMMddHHmmsszzzyyyy
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        val date: Date
        try {
            date = dateFormat.parse(dateTime)
        } catch (e: ParseException) {
            throw RuntimeException(e)
        }

        return date
    }

    fun getDateTime(dateTime: Date): String = ddMMyyyyHHmmss.format(dateTime)
}