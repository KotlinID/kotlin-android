package com.baculsoft.kotlin.android.internal.data.remote

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class TwitterSearchResponse {

    @JsonProperty(value = "statuses")
    lateinit var statuses: List<Statuses>

    @JsonProperty(value = "search_metadata")
    lateinit var searchMetadata: SearchMetadata

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Statuses {

        @JsonProperty(value = "created_at")
        lateinit var createdAt: String

        @JsonProperty(value = "text")
        lateinit var text: String
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class SearchMetadata {

        @JsonProperty(value = "count")
        var count: Int? = 0
    }
}