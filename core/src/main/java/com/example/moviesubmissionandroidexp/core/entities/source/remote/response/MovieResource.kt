package com.example.moviesubmissionandroidexp.core.entities.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResource(
    @SerializedName("id") var id : Int,
    @SerializedName("title") var title : String? = "No title",
    @SerializedName("backdrop_path") var backdropPath : String? = "",
    @SerializedName("overview") var overview : String? = "no overview",
    @SerializedName("popularity") var popularity : Double? = 0.0,
    @SerializedName("vote_average") var voteAverage: Double? = 0.0,
    @SerializedName("original_language") var originalLanguage : String? = "EN",
)