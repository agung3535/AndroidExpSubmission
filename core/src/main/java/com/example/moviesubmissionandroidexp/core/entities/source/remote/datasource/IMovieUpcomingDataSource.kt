package com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource

import com.example.moviesubmissionandroidexp.core.entities.source.remote.network.ApiResponse
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieResource
import kotlinx.coroutines.flow.Flow

interface IMovieUpcomingDataSource {
    fun getUpcomingMovie(): Flow<ApiResponse<List<MovieResource>>>
}