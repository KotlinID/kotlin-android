package com.baculsoft.kotlin.android.internal.api.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class TwitterSearchResponse {

    @JsonProperty(value = "statuses")
    var statuses: List<Statuses>? = null

    @JsonProperty(value = "search_metadata")
    var searchMetadata: SearchMetadata? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Statuses {

        @JsonProperty(value = "created_at")
        var createdAt: String = ""

        @JsonProperty(value = "text")
        var text: String = ""
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class SearchMetadata {

        @JsonProperty(value = "count")
        var count: Int = 0
    }
}