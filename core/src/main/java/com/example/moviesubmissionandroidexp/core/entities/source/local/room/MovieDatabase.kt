package com.example.moviesubmissionandroidexp.core.entities.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.CastFavMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteListCategoryEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.MovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.MoviePlayingRemoteKeys
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.ReviewFavMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.TempDeleteFav
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.UpcomingMovieEntity

@Database(entities = [MovieEntity::class,
    UpcomingMovieEntity::class,
    MoviePlayingRemoteKeys::class, FavoriteListCategoryEntity::class,
    FavoriteMovieEntity::class, CastFavMovieEntity::class, ReviewFavMovieEntity::class,
    TempDeleteFav::class],
    version = 4,
    exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun moviePlayingRemoteKeys(): MoviePlayingRemoteKeysDao
}