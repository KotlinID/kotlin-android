package com.baculsoft.kotlin.android.internal.data.local

import org.parceler.Parcel
import org.parceler.ParcelConstructor
import org.parceler.ParcelProperty

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Parcel(Parcel.Serialization.BEAN)
data class TwitterSearch @ParcelConstructor constructor(
        @ParcelProperty(value = "twitterSearchResults") val twitterSearchResults: List<TwitterSearchResult>,
        @ParcelProperty(value = "count") val count: Int
)