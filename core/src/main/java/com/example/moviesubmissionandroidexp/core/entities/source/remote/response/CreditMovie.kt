package com.example.moviesubmissionandroidexp.core.entities.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreditMovie(
    @SerializedName("id") val id: Long,
    @SerializedName("cast") val cast: List<CastResource>
)
