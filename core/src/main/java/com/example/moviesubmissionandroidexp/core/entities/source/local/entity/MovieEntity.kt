package com.example.moviesubmissionandroidexp.core.entities.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playingnow")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "playingId")
    var id: Int,
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
)