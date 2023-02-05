package com.example.moviesubmissionandroidexp.core.entities.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "cast_movie",
    foreignKeys =[
        ForeignKey(
            entity = FavoriteMovieEntity::class,
            parentColumns = ["fav_id"],
            childColumns = ["fk_favid"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class CastFavMovieEntity(
    @ColumnInfo(name = "castId")
    @PrimaryKey
    val castId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "original_name")
    val originalName: String,
    @ColumnInfo(name="profile_path")
    val profilePath: String? = null,
    @ColumnInfo(name = "fk_favid")
    val fkFavId: Int
)
