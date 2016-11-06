package com.baculsoft.kotlin.android.internal.pojo

import android.os.Parcel
import android.os.Parcelable

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
data class TwitterSearchResult(
        val text: String,
        val createdAt: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<TwitterSearchResult> = object : Parcelable.Creator<TwitterSearchResult> {
            override fun createFromParcel(source: Parcel): TwitterSearchResult? = TwitterSearchResult(source)
            override fun newArray(size: Int): Array<out TwitterSearchResult?> = arrayOfNulls(size)
        }
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