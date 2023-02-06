package com.example.moviesubmissionandroidexp.core.entities.repository.favoritemovie

import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.TempDeleteFav
import com.example.moviesubmissionandroidexp.core.presentation.model.CastDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteCategoryWithPreviewModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteDetailMovie
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewDomainModel
import kotlinx.coroutines.flow.Flow

interface IFavoriteMovieRepository {
    fun getFavoriteMovie(favId: Int): Flow<Resource<List<FavoriteMovieModel>>>
    fun getCategoryFavorite(): Flow<List<FavoriteCategoryWithPreviewModel>>
    fun getCategoryList(): Flow<List<FavoritListCategoryModel>>
    fun getDetailFavorite(movieId: Int): Flow<FavoriteDetailMovie>
    fun cekFavorite(movieId: Int): Flow<List<FavoriteMovieModel>>
    fun addFavorite(data: FavoriteMovieModel): Long
    fun addFavoriteCast(data: List<CastDomainModel>, fkId: Int): Flow<List<Long>>
    fun addFavoriteReview(data: List<ReviewDomainModel>, fkId: Int): Flow<List<Long>>
    //    fun addFavoriteWithCastReview(data: FavoriteMovieModel, favCast: List<CastDomainModel>, review: List<ReviewDomainModel>): Flow<Long>
    fun addCategoryFavorite(data: FavoritListCategoryModel): Flow<Long>
    fun deleteFavorite(data: List<FavoriteMovieModel>): Flow<Int>
    fun deleteCategoryFavorite(data: FavoritListCategoryModel): Flow<Int>
    fun getTempFavDelete(): Flow<Resource<List<FavoriteMovieModel>>>
    fun addTempFavDelete(data: TempDeleteFav): Flow<Int>
    fun deleteTempFavDelete(data: TempDeleteFav): Flow<Int>
}