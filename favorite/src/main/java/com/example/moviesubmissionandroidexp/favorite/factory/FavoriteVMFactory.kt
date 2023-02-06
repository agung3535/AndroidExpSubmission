package com.example.moviesubmissionandroidexp.favorite.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesubmissionandroidexp.core.domain.favoritemovie.IFavoriteMovieUsecase
import com.example.moviesubmissionandroidexp.favorite.viewmodel.favoritelist.FavoriteListViewmodel
import javax.inject.Inject

class FavoriteVMFactory @Inject constructor(private val favoriteMovieUsecase: IFavoriteMovieUsecase): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FavoriteListViewmodel::class.java) -> {
                FavoriteListViewmodel(favoriteMovieUsecase) as T
            }
            else -> throw Throwable("Unkown Viewmolde class: " + modelClass.name)
        }
    }
}