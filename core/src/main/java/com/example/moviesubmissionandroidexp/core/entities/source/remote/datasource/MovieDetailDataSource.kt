package com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import com.example.moviesubmissionandroidexp.core.entities.source.remote.network.ApiResponse
import com.example.moviesubmissionandroidexp.core.entities.source.remote.network.ApiService
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.CastResource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieDetail
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.ReviewResource
import com.example.moviesubmissionandroidexp.core.utils.ApiKeysSource
import kotlinx.coroutines.flow.flowOn

@Singleton
class MovieDetailDataSource @Inject constructor(
    private val apiService: ApiService
): IDetailMovieDataSource {

    var apiKey = ApiKeysSource.apiKeys(1)

    override fun getDetailMovie(movieId: String): Flow<ApiResponse<MovieDetail>> {
        return flow {
            try {
                val response = apiService.getDetailMovie(movieId,apiKey)
                if (response != null) {
                    emit(ApiResponse.Success(response))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getCast(movieId: String): Flow<ApiResponse<List<CastResource>>> {
        return flow {
            try {
                val response = apiService.getCastMovie(movieId,apiKey)
                if (response != null) {
                    val data = response.cast
                    if (data.isNotEmpty()) {
                        emit(ApiResponse.Success(data))
                    }else {
                        emit(ApiResponse.Empty)
                    }
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getReview(movieId: String): Flow<ApiResponse<List<ReviewResource>>> {
        return flow {
            try {
                val response = apiService.getReviews(movieId,apiKey)
                if (response != null) {
                    val data = response.results
                    if (data.isNotEmpty()) {
                        emit(ApiResponse.Success(data))
                    }else {
                        emit(ApiResponse.Empty)
                    }
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}