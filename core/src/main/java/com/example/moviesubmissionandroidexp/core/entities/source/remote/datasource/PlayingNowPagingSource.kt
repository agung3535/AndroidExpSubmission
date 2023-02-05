package com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.MovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.MoviePlayingRemoteKeys
import com.example.moviesubmissionandroidexp.core.entities.source.local.room.MovieDatabase
import com.example.moviesubmissionandroidexp.core.entities.source.remote.network.ApiService
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PlayingNowPagingSource @Inject constructor(
    private val database: MovieDatabase,
    private val apiService: ApiService, private val apiKeys: String): RemoteMediator<Int, MovieEntity>() {

//    val apiKeys = ApiKeysSource.apiKeys(1)


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        Log.d("dataaaa abc","apikey = ${apiKeys}")
        val page = when(loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeysForLastItem(state)
                val nextKey = remoteKeys?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            Log.d("dataaaa xyz","apikey = ${apiKeys} page = ${page}")
            val responseData = apiService.getPlayingMovies(apiKeys,page)
            val endOfPagingData = responseData.results.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.moviePlayingRemoteKeys().deleteAll()
                    database.movieDao().deleteAllPlaying()
                }
                val prevKeys = if (page == 1) null else page - 1
                val nextKeys = if (endOfPagingData) null else page + 1
                val keys = responseData.results.map {
                    MoviePlayingRemoteKeys(id = it.id.toString(), prevKey = prevKeys, nextKey = nextKeys)
                }
                database.moviePlayingRemoteKeys().insertAll(keys)
                database.movieDao().insertPlayingMovie(responseData.results.map { MovieEntity(it.id,it.title ?: "No title",it.backdropPath ?: "",it.overview ?: "no overview",
                    it.popularity ?: 0.0,it.voteAverage ?: 0.0,it.originalLanguage ?: "") })

            }
            Log.d("dataaaa apikey","end of page = ${endOfPagingData}")
            return MediatorResult.Success(endOfPaginationReached = endOfPagingData)
        }catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysForLastItem(state: PagingState<Int, MovieEntity>): MoviePlayingRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { data ->
            database.moviePlayingRemoteKeys().getRemoteKeysId(data.id.toString())
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieEntity>): MoviePlayingRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            database.moviePlayingRemoteKeys().getRemoteKeysId(data.id.toString())
        }
    }
    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieEntity>): MoviePlayingRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.moviePlayingRemoteKeys().getRemoteKeysId(id.toString())
            }
        }
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}