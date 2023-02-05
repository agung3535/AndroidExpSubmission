package com.example.moviesubmissionandroidexp.core.di

import com.example.moviesubmissionandroidexp.core.entities.repository.detailmovie.DetailMovieRepository
import com.example.moviesubmissionandroidexp.core.entities.repository.detailmovie.IDetailMovieRepository
import com.example.moviesubmissionandroidexp.core.entities.repository.favoritemovie.FavoriteMovieRepository
import com.example.moviesubmissionandroidexp.core.entities.repository.favoritemovie.IFavoriteMovieRepository
import com.example.moviesubmissionandroidexp.core.entities.repository.playing.IPlayingRepository
import com.example.moviesubmissionandroidexp.core.entities.repository.playing.PlayingRepository
import com.example.moviesubmissionandroidexp.core.entities.repository.upcoming.IUpcomingRepository
import com.example.moviesubmissionandroidexp.core.entities.repository.upcoming.UpcomingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMovieRepo(playingRepository: PlayingRepository): IPlayingRepository

    @Binds
    abstract fun provideUpcomingRepo(upcomingRepository: UpcomingRepository): IUpcomingRepository

    @Binds
    abstract fun provideFavoriteRepo(favoriteMovieRepository: FavoriteMovieRepository): IFavoriteMovieRepository

    @Binds
    abstract fun provideDetailMovieRepo(detailMovieRepository: DetailMovieRepository): IDetailMovieRepository
}