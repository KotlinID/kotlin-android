package com.baculsoft.kotlin.android.internal.data.local

import org.parceler.Parcel
import org.parceler.ParcelConstructor
import org.parceler.ParcelProperty

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Parcel(Parcel.Serialization.BEAN)
data class TwitterSearchResult @ParcelConstructor constructor(
        @ParcelProperty(value = "text") val text: String,
        @ParcelProperty(value = "createdAt") val createdAt: String
)