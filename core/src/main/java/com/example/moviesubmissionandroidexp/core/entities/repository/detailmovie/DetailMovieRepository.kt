package com.example.moviesubmissionandroidexp.core.entities.repository.detailmovie

import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource.IDetailMovieDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.network.ApiResponse
import com.example.moviesubmissionandroidexp.core.presentation.model.CastDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.DetailMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewDomainModel
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailMovieRepository @Inject constructor(
    private val remote: IDetailMovieDataSource
): IDetailMovieRepository {
    override fun getDetailMovie(movieId: String): Flow<Resource<DetailMovieModel>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = remote.getDetailMovie(movieId)
                data.collect { collectData ->
                    when(collectData) {
                        is ApiResponse.Success -> {
                            emit(Resource.Success(collectData.data.toDomain()))
                        }
                        is ApiResponse.Empty -> {
                            emit(Resource.Success(null))
                        }
                        is ApiResponse.Error -> Resource.Error(collectData.errorMessage.toString(),null)
                    }
                }
            }catch (e: Exception) {
                emit(Resource.Error(e.message.toString(),null))
            }
        }
    }

    override fun getCastMovie(movieId: String): Flow<Resource<List<CastDomainModel>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = remote.getCast(movieId)
                data.collect { collectData ->
                    when(collectData) {
                        ApiResponse.Empty -> {
                            emit(Resource.Success(null))
                        }
                        is ApiResponse.Error -> {
                            emit(Resource.Error(collectData.errorMessage))
                        }
                        is ApiResponse.Success -> {

                            emit(Resource.Success(collectData.data.map { it.toDomain() }))
                        }
                    }
                }
            }catch (e: Exception) {
                emit(Resource.Error(e.message.toString(),null))
            }
        }
    }

    override fun getReview(movieId: String): Flow<Resource<List<ReviewDomainModel>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = remote.getReview(movieId)
                data.collect { collectData ->
                    when(collectData) {
                        ApiResponse.Empty -> {
                            emit(Resource.Success(null))
                        }
                        is ApiResponse.Error -> {
                            emit(Resource.Error(collectData.errorMessage))
                        }
                        is ApiResponse.Success -> {
                            val data = collectData.data.map {
                                it.toDomain()
                            }
                            emit(Resource.Success(collectData.data.map { it.toDomain() }))
                        }
                    }
                }
            }catch (e: Exception) {
                emit(Resource.Error(e.message.toString(),null))
            }
        }
    }

}