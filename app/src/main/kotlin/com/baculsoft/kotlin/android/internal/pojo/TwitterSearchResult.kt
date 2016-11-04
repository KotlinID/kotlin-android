package com.baculsoft.kotlin.android.internal.pojo

import android.os.Parcel
import android.os.Parcelable
import com.baculsoft.kotlin.android.utils.extensions.createParcel

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
data class TwitterSearchResult(
        val text: String,
        val createdAt: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR = createParcel(::TwitterSearchResult)
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(text)
        dest.writeString(createdAt)
    }

    override fun describeContents() = 0
}