package com.example.moviesubmissionandroidexp.core.entities.repository.upcoming

import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.presentation.model.UpcomingModel
import kotlinx.coroutines.flow.Flow

interface IUpcomingRepository {
    fun getUpcomingMovie(): Flow<Resource<List<UpcomingModel>>>
}