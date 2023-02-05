package com.example.moviesubmissionandroidexp.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesubmissionandroidexp.core.domain.upcoming.IUpcomingMovieUsecase
import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.presentation.model.UpcomingModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UpcomingMovieViewModel(private val upcomingMovieUsecase: IUpcomingMovieUsecase): ViewModel() {

    val upcomingData = MutableStateFlow<Resource<List<UpcomingModel>>?>(null)

    fun getUpcomingMovie() = viewModelScope.launch {
        upcomingMovieUsecase.getUpcomingMovie().collect { dataState ->
            upcomingData.value = dataState
        }
    }


}