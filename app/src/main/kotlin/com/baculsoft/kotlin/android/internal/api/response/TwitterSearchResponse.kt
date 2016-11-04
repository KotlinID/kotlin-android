package com.baculsoft.kotlin.android.internal.api.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class TwitterSearchResponse {

    @JsonProperty(value = "statuses")
    val statuses: List<Statuses> = arrayListOf(Statuses())

    @JsonProperty(value = "search_metadata")
    val searchMetadata: SearchMetadata = SearchMetadata()

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Statuses {

        @JsonProperty(value = "created_at")
        val createdAt: String = ""

        @JsonProperty(value = "text")
        val text: String = ""
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class SearchMetadata {

        @JsonProperty(value = "count")
        val count: Int = 0
    }
}