package com.example.moviesubmissionandroidexp.core.presentation.model

data class UpcomingModel(
    var id : Int,
    var title : String,
    var backdropPath : String,
    var overview : String,
    var popularity : Double,
    var voteAverage: Double,
    var originalLanguage : String,
)