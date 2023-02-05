package com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.upcomingmovie

import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.UpcomingMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpcomingMovieLocalDataSource @Inject constructor(private val movieDao: MovieDao): IUpcomingMovieLocalDataSource {

    override fun getUpcomingMovie(): Flow<List<UpcomingMovieEntity>> {
        return movieDao.getUpcomingMovie()
    }

    override suspend fun insertUpMovie(data: List<UpcomingMovieEntity>) {
        return movieDao.insertUpcomingMovie(data)
    }
}