package com.example.moviesubmissionandroidexp.movie.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesubmissionandroidexp.core.domain.upcoming.IUpcomingMovieUsecase
import com.example.moviesubmissionandroidexp.movie.viewmodel.UpcomingMovieViewModel
import javax.inject.Inject

class UpcomingVMFactory @Inject constructor(private val upcomingMovieUsecase: IUpcomingMovieUsecase): ViewModelProvider.NewInstanceFactory()
{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  when {
            modelClass.isAssignableFrom(UpcomingMovieViewModel::class.java) -> {
                UpcomingMovieViewModel(upcomingMovieUsecase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }



}