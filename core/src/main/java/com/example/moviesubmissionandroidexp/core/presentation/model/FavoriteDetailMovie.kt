package com.example.moviesubmissionandroidexp.core.presentation.model

data class FavoriteDetailMovie(
    val detailMovie: FavoriteMovieModel,
    val castMovie: List<CastFavMovieDomainModel>,
    val reviewMovie: List<ReviewFavMovieDomainModel>
)