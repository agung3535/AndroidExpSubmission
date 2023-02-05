package com.example.moviesubmissionandroidexp.core.domain.upcoming

import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.presentation.model.UpcomingModel
import kotlinx.coroutines.flow.Flow

interface IUpcomingMovieUsecase {
    fun getUpcomingMovie(): Flow<Resource<List<UpcomingModel>>>
}