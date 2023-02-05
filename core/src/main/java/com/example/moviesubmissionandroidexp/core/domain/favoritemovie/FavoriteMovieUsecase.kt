package com.example.moviesubmissionandroidexp.core.domain.favoritemovie

import com.example.moviesubmissionandroidexp.core.entities.repository.favoritemovie.IFavoriteMovieRepository
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteCategoryWithPreviewModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteDetailMovie
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteMovieUsecase @Inject constructor(private val repository: IFavoriteMovieRepository): IFavoriteMovieUsecase {
    override fun getFavoriteMovie(favId: Int): Flow<List<FavoriteMovieModel>> {
        return repository.getFavoriteMovie(favId)
    }

    override fun getCategoryFavorite(): Flow<List<FavoriteCategoryWithPreviewModel>> {
        return repository.getCategoryFavorite()
    }

    override fun getDetailFavMovie(movieId: Int): Flow<FavoriteDetailMovie> {
        return repository.getDetailFavorite(movieId)
    }

    override fun cekFavorite(movieId: Int): Flow<List<FavoriteMovieModel>> {
        return repository.cekFavorite(movieId)
    }

    override fun getCategoryList(): Flow<List<FavoritListCategoryModel>> {
        return repository.getCategoryList()
    }


}