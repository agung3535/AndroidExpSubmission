package com.example.moviesubmissionandroidexp.core.entities.repository.playing

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviesubmissionandroidexp.core.entities.NetworkBoundResource
import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.playingmovie.IPlayingMovieLocalDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource.IMoviePlayingDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.network.ApiResponse
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieResource
import com.example.moviesubmissionandroidexp.core.presentation.model.PlayingNowModel
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toDomain
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toEntities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayingRepository @Inject constructor(
    private val remote: IMoviePlayingDataSource,
    private val local: IPlayingMovieLocalDataSource,
): IPlayingRepository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getPlayingMovie(): Flow<PagingData<PlayingNowModel>> {
        return Pager(config = PagingConfig(
            pageSize = 5
        ), remoteMediator = remote.getPlayingPaging(),
            pagingSourceFactory = {
                local.getPlayingMovie()
            }).flow.mapLatest { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }
    }

    override fun getPlayingNotPaging(): Flow<Resource<List<PlayingNowModel>>> {
        return  object : NetworkBoundResource<List<PlayingNowModel>, List<MovieResource>>() {
            override fun loadFromDB(): Flow<List<PlayingNowModel>> {
                return local.getPlayingNotPaging().map {
                    it.map {
                        it.toDomain()
                    }
                }
            }

            override fun shouldFetch(data: List<PlayingNowModel>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResource>>> {
                Log.d("dataaaa","remote aja ${remote.getPlayingNowMovie()}")
                return remote.getPlayingNowMovie()
            }

            override suspend fun saveCallResult(data: List<MovieResource>) {
                val movie = data.map { it.toEntities() }
                GlobalScope.launch {
                    CoroutineScope(Dispatchers.IO).launch {
                        local.insertMovie(movie)
                    }

                }

            }

        }.asFlow()
    }
}