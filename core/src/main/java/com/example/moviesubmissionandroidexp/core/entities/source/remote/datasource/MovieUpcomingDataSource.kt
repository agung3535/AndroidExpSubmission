package com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource

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
class MovieUpcomingDataSource @Inject constructor(private val apiService: ApiService): IMovieUpcomingDataSource {

    private val apiKeys = ApiKeysSource.apiKeys(1)

    override fun getUpcomingMovie(): Flow<ApiResponse<List<MovieResource>>> {
        return flow {
            try {
                val remoteResponse = apiService.getUpComing(apiKeys)
                if (remoteResponse.results.isNotEmpty()) {
                    emit(ApiResponse.Success(remoteResponse.results))
                }else {
                    emit(ApiResponse.Empty)
                }

            }catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}