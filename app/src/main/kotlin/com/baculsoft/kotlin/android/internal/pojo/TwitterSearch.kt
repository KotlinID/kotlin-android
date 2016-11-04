package com.baculsoft.kotlin.android.internal.pojo

import android.os.Parcel
import android.os.Parcelable
import com.baculsoft.kotlin.android.utils.extensions.createParcel

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
data class TwitterSearch(val results: List<TwitterSearchResult>) : Parcelable {

    companion object {
        @JvmField val CREATOR = createParcel(::TwitterSearch)
    }

    constructor(source: Parcel) : this(
            arrayListOf<TwitterSearchResult>().apply {
                source.readTypedList(this, TwitterSearchResult.CREATOR)
            }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(results)
    }

    override fun describeContents() = 0
}