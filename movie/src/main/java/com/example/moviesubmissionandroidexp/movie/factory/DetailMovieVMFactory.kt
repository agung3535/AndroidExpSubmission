package com.example.moviesubmissionandroidexp.movie.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesubmissionandroidexp.core.domain.detailmovie.IDetailMovieUsecase
import com.example.moviesubmissionandroidexp.movie.viewmodel.DetailMovieViewmodel
import javax.inject.Inject

class DetailMovieVMFactory @Inject constructor(private val detailMovieUsecase: IDetailMovieUsecase): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailMovieViewmodel::class.java) -> {
                DetailMovieViewmodel(detailMovieUsecase) as T
            } else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}