package com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.MovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.remote.network.ApiResponse
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieResource
import kotlinx.coroutines.flow.Flow

interface IMoviePlayingDataSource {
    suspend fun getPlayingNowMovie(): Flow<ApiResponse<List<MovieResource>>>
    @OptIn(ExperimentalPagingApi::class)
    suspend fun getPlayingPaging(): RemoteMediator<Int, MovieEntity>
}