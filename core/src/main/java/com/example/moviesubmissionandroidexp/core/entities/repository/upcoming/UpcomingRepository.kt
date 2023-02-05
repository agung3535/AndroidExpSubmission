package com.example.moviesubmissionandroidexp.core.entities.repository.upcoming

import com.example.moviesubmissionandroidexp.core.entities.NetworkBoundResource
import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.upcomingmovie.UpcomingMovieLocalDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource.MovieUpcomingDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.network.ApiResponse
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieResource
import com.example.moviesubmissionandroidexp.core.presentation.model.UpcomingModel
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toDomain
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toUpEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpcomingRepository @Inject constructor(
    private val remote: MovieUpcomingDataSource,
    private val local: UpcomingMovieLocalDataSource
): IUpcomingRepository {
    override fun getUpcomingMovie(): Flow<Resource<List<UpcomingModel>>> {
        return object: NetworkBoundResource<List<UpcomingModel>, List<MovieResource>>() {
            override fun loadFromDB(): Flow<List<UpcomingModel>> {
                return local.getUpcomingMovie().map { local ->
                    local.map { it.toDomain() }
                }
            }

            override fun shouldFetch(data: List<UpcomingModel>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResource>>> {
                return remote.getUpcomingMovie()
            }

            override suspend fun saveCallResult(data: List<MovieResource>) {
                val callRes = data.map { it.toUpEntities() }
                local.insertUpMovie(callRes)
            }

        }.asFlow()
    }
}