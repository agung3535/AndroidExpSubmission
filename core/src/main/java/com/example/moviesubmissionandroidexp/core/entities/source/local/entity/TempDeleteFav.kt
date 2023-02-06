package com.example.moviesubmissionandroidexp.core.entities.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temp_delete_movie_fav")
data class TempDeleteFav(
    @ColumnInfo(name = "fav_id")
    @PrimaryKey(autoGenerate = true)
    val favId: Int,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "backdrop_path")
    var backdropPath : String,
    @ColumnInfo(name = "overview")
    var overview : String,
    @ColumnInfo(name = "popularity")
    var popularity : Double,
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,
    @ColumnInfo(name = "original_language")
    var originalLanguage : String,
    @ColumnInfo(name = "fav_category_id")
    val favCategoryMovieId: Int,
    @ColumnInfo(name = "movie_id")
    val movieId: Int
)