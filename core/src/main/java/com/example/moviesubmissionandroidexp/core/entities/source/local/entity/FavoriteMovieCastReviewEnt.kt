package com.example.moviesubmissionandroidexp.core.entities.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FavoriteMovieCastReviewEnt(
    @Embedded
    val favMovie: FavoriteMovieEntity,
    @Relation(parentColumn = "fav_id",
        entityColumn = "fk_favid")
    val castMovie: List<CastFavMovieEntity>,
    @Relation(parentColumn = "fav_id",
        entityColumn = "fk_favid")
    val reviewMovie: List<ReviewFavMovieEntity>
)