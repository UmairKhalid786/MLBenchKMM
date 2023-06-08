package com.techlads.mlbench.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContentResponse(val results: List<Content>) {
    @Serializable
    data class Content(
        @SerialName("id") var id: Int? = null,
        @SerialName("title") var title: String? = null,
        @SerialName("poster_path") var posterPath: String? = null,
        @SerialName("media_type") var mediaType: String? = null,
        @SerialName("release_date") var releaseDate: String? = null,
    )
}