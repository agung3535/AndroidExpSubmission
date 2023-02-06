package com.example.moviesubmissionandroidexp.favorite.viewmodel.favoritelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesubmissionandroidexp.core.domain.favoritemovie.IFavoriteMovieUsecase
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteCategoryWithPreviewModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteDetailMovie
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteListViewmodel(private val favoriteMovieUsecase: IFavoriteMovieUsecase): ViewModel() {

    val favoriteData: MutableStateFlow<List<FavoriteMovieModel>?> = MutableStateFlow(null)
    val categoryList: MutableStateFlow<List<FavoriteCategoryWithPreviewModel>?> = MutableStateFlow(null)
    val categoryListName: MutableStateFlow<List<FavoritListCategoryModel>?> = MutableStateFlow(null)
    val isFavorite: MutableStateFlow<List<FavoriteMovieModel>?> = MutableStateFlow(null)
    val detailFavoriteData: MutableStateFlow<FavoriteDetailMovie?> = MutableStateFlow(null)


    fun getFavoriteMovie(favId: Int) = viewModelScope.launch {
        favoriteMovieUsecase.getFavoriteMovie(favId).collectLatest {
            favoriteData.value = it
        }
    }

    fun getCategory() = viewModelScope.launch {
        favoriteMovieUsecase.getCategoryFavorite().collectLatest {
            categoryList.value = it
        }
    }

    fun getCategoryList() = viewModelScope.launch {
        favoriteMovieUsecase.getCategoryList().collectLatest {
            categoryListName.value = it
        }
    }

    fun cekFavorite(movieId: Int) = viewModelScope.launch {
        favoriteMovieUsecase.cekFavorite(movieId).collect {
            if (it.size != 0) {
                isFavorite.value = it
            }else {
                isFavorite.value = null
            }
        }
    }

    fun getDetailFavoriteMovie(movieId: Int) = viewModelScope.launch {
        favoriteMovieUsecase.getDetailFavMovie(movieId).collectLatest {
            detailFavoriteData.value = it
        }
    }

}