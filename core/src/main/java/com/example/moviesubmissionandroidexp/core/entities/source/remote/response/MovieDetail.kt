package com.example.moviesubmissionandroidexp.core.entities.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id") var id : Int,
    @SerializedName("title") var title : String,
    @SerializedName("backdrop_path") var backdropPath : String,
    @SerializedName("overview") var overview : String,
    @SerializedName("popularity") var popularity : Double,
    @SerializedName("original_language") var originalLanguage : String,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("genres") var genres : List<MovieGenre>,
)