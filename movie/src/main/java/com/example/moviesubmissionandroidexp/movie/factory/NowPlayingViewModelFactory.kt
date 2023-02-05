package com.example.moviesubmissionandroidexp.movie.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesubmissionandroidexp.core.domain.playing.IPlayingMovieUsecase
import com.example.moviesubmissionandroidexp.movie.viewmodel.NowPlayingViewmodel
import javax.inject.Inject

class NowPlayingViewModelFactory @Inject constructor(private val playingMovieUsecase: IPlayingMovieUsecase): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(NowPlayingViewmodel::class.java) -> {
                NowPlayingViewmodel(playingMovieUsecase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}