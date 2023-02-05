package com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.playingmovie

import androidx.paging.PagingSource
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.MovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayingMovieLocalDataSource @Inject constructor(val movieDao: MovieDao): IPlayingMovieLocalDataSource {
    override fun getPlayingMovie(): PagingSource<Int, MovieEntity> {
        return movieDao.getMovie()
    }

    override fun getPlayingNotPaging(): Flow<List<MovieEntity>> {
        return movieDao.getMovieNotPaging()
    }

    override suspend fun insertMovie(data: List<MovieEntity>) {
        movieDao.insertPlayingMovie(data)
    }
}