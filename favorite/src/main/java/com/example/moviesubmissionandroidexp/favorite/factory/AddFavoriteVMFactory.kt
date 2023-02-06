package com.example.moviesubmissionandroidexp.favorite.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesubmissionandroidexp.core.domain.addfavoritemovie.IAddFavoriteMovieUsecase
import com.example.moviesubmissionandroidexp.favorite.viewmodel.addfavorite.AddFavoriteMovieViewmodel
import javax.inject.Inject

class AddFavoriteVMFactory @Inject constructor(private val addFavoriteMovieUsecase: IAddFavoriteMovieUsecase): ViewModelProvider.NewInstanceFactory()  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AddFavoriteMovieViewmodel::class.java) -> {
                AddFavoriteMovieViewmodel(addFavoriteMovieUsecase) as T
            }
            else -> throw Throwable("Unkown Viewmolde class: " + modelClass.name)
        }
    }



}