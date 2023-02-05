package com.example.moviesubmissionandroidexp.movie.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesubmissionandroidexp.core.domain.playing.IPlayingMovieUsecase
import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.presentation.model.PlayingNowModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NowPlayingViewmodel constructor(val movieUsecase: IPlayingMovieUsecase): ViewModel() {
    val playingData: MutableStateFlow<PagingData<PlayingNowModel>?> = MutableStateFlow(null)

    fun getPlayingMovie() = viewModelScope.launch {
        Log.d("dataaaa","playing di call")
        movieUsecase.getPlayingMovie().cachedIn(viewModelScope).collect { pagingData ->
            Log.d("dataaaa","playing di call data ${pagingData}")
            playingData.value = pagingData
        }
    }

    fun getPlayingNotPaging() = viewModelScope.launch {

        movieUsecase.getPlayingMovieNotPaging().collect { dataState ->

            when(dataState) {
                is Resource.Success -> {
                    Log.d("dataaaa","masuk success ${dataState.data?.size}")
                }
                is Resource.Error -> {
                    Log.d("dataaaa","masuk error ${dataState.message}")
                }
                is Resource.Loading -> {
                    Log.d("dataaaa","masuk loading ${dataState.message}")
                }
            }
        }
    }
}