package com.example.moviesubmissionandroidexp.core.presentation.model

data class FavoriteMovieModel(
    val favId: Int = 0,
    var title : String,
    var backdropPath : String,
    var overview : String,
    var popularity : Double,
    var voteAverage: Double,
    var originalLanguage : String,
    val favCategoryMovieId: Int,
    var movieId: Int
)