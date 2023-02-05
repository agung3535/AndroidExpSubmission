package com.example.moviesubmissionandroidexp.core.entities.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse<T>(
    @SerializedName("page") var page : Int,
    @SerializedName("results") var results : List<T>,
    @SerializedName("total_pages") var totalPages : Int,
    @SerializedName("total_results") var totalResults : Int
)