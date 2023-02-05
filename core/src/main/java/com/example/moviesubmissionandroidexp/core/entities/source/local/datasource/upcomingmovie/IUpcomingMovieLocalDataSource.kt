package com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.upcomingmovie

import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow

interface IUpcomingMovieLocalDataSource {
    fun getUpcomingMovie(): Flow<List<UpcomingMovieEntity>>
    suspend fun insertUpMovie(data: List<UpcomingMovieEntity>)
}