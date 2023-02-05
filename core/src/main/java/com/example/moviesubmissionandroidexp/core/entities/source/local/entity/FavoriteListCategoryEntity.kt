package com.example.moviesubmissionandroidexp.core.entities.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "favorite_category")
data class FavoriteListCategoryEntity(
    @ColumnInfo(name = "fav_caretegorymovie_id")
    @PrimaryKey(autoGenerate = true)
    val favCategoryMovieId: Int = 0,

    @ColumnInfo(name = "categoryname")
    val categoryName: String
)

data class OneToManyFavCategoryAndListMovieFavorite(
    @Embedded
    val favCategory: FavoriteListCategoryEntity,

    @Relation(parentColumn = "fav_caretegorymovie_id",
        entityColumn = "fav_category_id")
    val movie: List<FavoriteMovieEntity>
)