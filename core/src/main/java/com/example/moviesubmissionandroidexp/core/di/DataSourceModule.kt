package com.example.moviesubmissionandroidexp.core.di

import com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.favoritemovie.FavoriteMovieLocalDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.favoritemovie.IFavoriteMovieLocalDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.playingmovie.IPlayingMovieLocalDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.playingmovie.PlayingMovieLocalDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.upcomingmovie.IUpcomingMovieLocalDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.upcomingmovie.UpcomingMovieLocalDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource.IDetailMovieDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource.IMoviePlayingDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource.IMovieUpcomingDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource.MovieDetailDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource.MoviePlayingDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.datasource.MovieUpcomingDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideMovieDataSource(dataSource: MoviePlayingDataSource): IMoviePlayingDataSource

    @Binds
    abstract fun provideLocalMovieDataSource(dataSource: PlayingMovieLocalDataSource): IPlayingMovieLocalDataSource
    //
    @Binds
    abstract fun provideUpcomingMovie(dataSource: MovieUpcomingDataSource): IMovieUpcomingDataSource
    //
    @Binds
    abstract fun provideLocalUpcomingDataSource(dataSource: UpcomingMovieLocalDataSource): IUpcomingMovieLocalDataSource

    @Binds
    abstract fun provideLocalFavoriteDataSource(dataSource: FavoriteMovieLocalDataSource): IFavoriteMovieLocalDataSource

    @Binds
    abstract fun provideDetailMovieDataSource(dataSource: MovieDetailDataSource): IDetailMovieDataSource

}