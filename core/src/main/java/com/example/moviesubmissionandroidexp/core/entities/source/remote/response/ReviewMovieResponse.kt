package com.example.moviesubmissionandroidexp.core.entities.source.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewMovieResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("page") var page : Long,
    @SerializedName("results") var results : List<ReviewResource>,
    @SerializedName("total_pages") var totalPages : Long,
    @SerializedName("total_results") var totalResults : Long
)
