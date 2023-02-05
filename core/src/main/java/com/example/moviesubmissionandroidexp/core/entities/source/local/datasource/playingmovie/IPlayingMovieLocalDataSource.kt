package com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.playingmovie

import androidx.paging.PagingSource
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface IPlayingMovieLocalDataSource {
    fun getPlayingMovie(): PagingSource<Int, MovieEntity>
    fun getPlayingNotPaging(): Flow<List<MovieEntity>>
    suspend fun insertMovie(data: List<MovieEntity>)
}