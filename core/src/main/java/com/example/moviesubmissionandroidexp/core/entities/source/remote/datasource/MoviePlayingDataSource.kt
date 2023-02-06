package com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.example.moviesubmissionandroidexp.core.BuildConfig
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.MovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.room.MovieDatabase
import com.example.moviesubmissionandroidexp.core.entities.source.remote.network.ApiResponse
import com.example.moviesubmissionandroidexp.core.entities.source.remote.network.ApiService
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieResource
import com.example.moviesubmissionandroidexp.core.utils.ApiKeysSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePlayingDataSource @Inject constructor(
    private val apiService: ApiService,
    private val db: MovieDatabase
): IMoviePlayingDataSource {

    var apiKey = BuildConfig.TheMovieDBApi

    override suspend fun getPlayingNowMovie(): Flow<ApiResponse<List<MovieResource>>> {
        return flow {
            try {
                val response = apiService.getPlayingMovies(apiKey,1)
                Log.d("dataaa","response = ${response}")
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(data))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    @ExperimentalPagingApi
    override suspend fun getPlayingPaging(): RemoteMediator<Int, MovieEntity> {
        return PlayingNowPagingSource(db,apiService,apiKey)
    }
}