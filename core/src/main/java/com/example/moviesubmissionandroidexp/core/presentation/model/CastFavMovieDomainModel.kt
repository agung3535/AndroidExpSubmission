package com.example.moviesubmissionandroidexp.core.presentation.model

data class CastFavMovieDomainModel(
    val castId: Int,
    val name: String,
    val originalName: String,
    val profilePath: String? = null,
    val fkFavId: Int
)
