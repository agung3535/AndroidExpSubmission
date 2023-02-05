package com.example.moviesubmissionandroidexp.core.entities.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "review_movie",
    foreignKeys = [ForeignKey(
        entity = FavoriteMovieEntity::class,
        parentColumns = ["fav_id"],
        childColumns = ["fk_favid"],
        onDelete = ForeignKey.CASCADE
    )])
data class ReviewFavMovieEntity(
    @ColumnInfo(name = "review_id")
    @PrimaryKey
    val reviewId: Int,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "author_image")
    val avatarPath: String? = null,
    @ColumnInfo(name = "rating")
    val rating: Double? = null,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "fk_favid")
    val fkFavId: Int
)