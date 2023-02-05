package com.example.moviesubmissionandroidexp.core.domain.addfavoritemovie

import com.example.moviesubmissionandroidexp.core.presentation.model.CastDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewDomainModel
import kotlinx.coroutines.flow.Flow

interface IAddFavoriteMovieUsecase {
    fun addFavorite(data: FavoriteMovieModel): Long
    fun addCategoryFavorite(data: FavoritListCategoryModel): Flow<Long>
    fun addCastFavoriteMovie(data: List<CastDomainModel>, fkId: Int): Flow<List<Long>>
    fun addReviewFavoriteMovie(data: List<ReviewDomainModel>, fkId: Int): Flow<List<Long>>
    fun deleteFavorite(data: List<FavoriteMovieModel>): Flow<Int>
    fun deleteCategoryFavorite(data: FavoritListCategoryModel): Flow<Int>
}