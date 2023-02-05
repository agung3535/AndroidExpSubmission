package com.example.moviesubmissionandroidexp.core.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayingNowModel(
    var id : Int,
    var title : String,
    var backdropPath : String,
    var overview : String,
    var popularity : Double,
    var voteAverage: Double,
    var originalLanguage : String,
): Parcelable