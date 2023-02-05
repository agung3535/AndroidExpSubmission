package com.example.moviesubmissionandroidexp.core.domain.favoritemovie

import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteCategoryWithPreviewModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteDetailMovie
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import kotlinx.coroutines.flow.Flow

interface IFavoriteMovieUsecase {
    fun getFavoriteMovie(favId: Int): Flow<List<FavoriteMovieModel>>
    fun getCategoryFavorite(): Flow<List<FavoriteCategoryWithPreviewModel>>
    fun getDetailFavMovie(movieId: Int): Flow<FavoriteDetailMovie>
    fun cekFavorite(movieId: Int): Flow<List<FavoriteMovieModel>>
    fun getCategoryList(): Flow<List<FavoritListCategoryModel>>

}