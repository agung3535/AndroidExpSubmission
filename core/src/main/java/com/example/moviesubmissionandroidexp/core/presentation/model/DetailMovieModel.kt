package com.example.moviesubmissionandroidexp.core.presentation.model

data class DetailMovieModel(
    var id : Int,
    var title : String,
    var backdropPath : String,
    var overview : String,
    var popularity : Double,
    var originalLanguage : String,
    var voteAverage: Double,
    var genres : List<GenreDomainModel>,
)