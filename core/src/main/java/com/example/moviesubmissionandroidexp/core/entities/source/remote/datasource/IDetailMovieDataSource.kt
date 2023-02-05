package com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource

import com.example.moviesubmissionandroidexp.core.entities.source.remote.network.ApiResponse
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.CastResource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieDetail
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.ReviewResource
import kotlinx.coroutines.flow.Flow

interface IDetailMovieDataSource {
    fun getDetailMovie(movieId: String): Flow<ApiResponse<MovieDetail>>
    fun getCast(movieId: String): Flow<ApiResponse<List<CastResource>>>
    fun getReview(movieId: String): Flow<ApiResponse<List<ReviewResource>>>
}