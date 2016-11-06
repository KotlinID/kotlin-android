package com.baculsoft.kotlin.android.internal.pojo

import android.os.Parcel
import android.os.Parcelable

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
data class TwitterSearch(
        val results: List<TwitterSearchResult>,
        val count: Int
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<TwitterSearch> = object : Parcelable.Creator<TwitterSearch> {
            override fun createFromParcel(source: Parcel): TwitterSearch? = TwitterSearch(source)
            override fun newArray(size: Int): Array<out TwitterSearch?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            arrayListOf<TwitterSearchResult>().apply {
                source.readTypedList(this, TwitterSearchResult.CREATOR)
            },
            source.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(results)
        dest.writeInt(count)
    }

    override fun describeContents() = 0
}