package com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.favoritemovie

import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.CastFavMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteListCategoryEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteMovieCastReviewEnt
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.OneToManyFavCategoryAndListMovieFavorite
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.ReviewFavMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.TempDeleteFav
import kotlinx.coroutines.flow.Flow

interface IFavoriteMovieLocalDataSource {
    fun getFavMovie(favId: Int): List<FavoriteMovieEntity>
    fun getFavCategoryMovie(): Flow<List<OneToManyFavCategoryAndListMovieFavorite>>
    fun getCategoryList(): Flow<List<FavoriteListCategoryEntity>>
    fun getDetailFavMovie(movieId: Int): Flow<FavoriteMovieCastReviewEnt>
    fun cekFavorite(movieId: Int): Flow<List<FavoriteMovieEntity>>
    fun addFavCategoryMovie(data: FavoriteListCategoryEntity): Flow<Long>
    fun addFavMovie(data: FavoriteMovieEntity): Long
    fun addFavCastMovie(data: List<CastFavMovieEntity>): Flow<List<Long>>
    fun addFavReviewMovie(data: List<ReviewFavMovieEntity>): Flow<List<Long>>
    fun deleteFavMovie(data: List<FavoriteMovieEntity>): Flow<Int>
    fun deleteCategoryFavMovie(data: FavoriteListCategoryEntity): Flow<Int>

    fun getTempFavDelete(): List<TempDeleteFav>
    fun addTempFavDelete(data: TempDeleteFav): Flow<Int>

    fun deleteTempFavDelete(data: TempDeleteFav): Flow<Int>
}