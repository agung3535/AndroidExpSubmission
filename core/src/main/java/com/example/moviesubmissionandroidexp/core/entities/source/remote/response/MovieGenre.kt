package com.example.moviesubmissionandroidexp.core.entities.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieGenre(
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String
)