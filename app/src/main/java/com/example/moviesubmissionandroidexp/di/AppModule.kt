package com.example.moviesubmissionandroidexp.di

import com.example.moviesubmissionandroidexp.core.domain.addfavoritemovie.AddFavoriteMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.addfavoritemovie.IAddFavoriteMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.detailmovie.DetailMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.detailmovie.IDetailMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.favoritemovie.FavoriteMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.favoritemovie.IFavoriteMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.playing.IPlayingMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.playing.PlayingMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.upcoming.IUpcomingMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.upcoming.UpcomingMovieUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun providePlayingMovieUsecase(playingMovieUsecase: PlayingMovieUsecase): IPlayingMovieUsecase

    @Binds
    @Singleton
    abstract fun provideUpcomingMovieUsecase(upcomingMovieUsecase: UpcomingMovieUsecase): IUpcomingMovieUsecase

    @Binds
    @Singleton
    abstract fun provideFavoriteMovieUsecase(favoriteMovieUsecase: FavoriteMovieUsecase): IFavoriteMovieUsecase

    @Binds
    @Singleton
    abstract fun provideAddFavoriteMovieUsecase(addFavoriteMovieUsecase: AddFavoriteMovieUsecase): IAddFavoriteMovieUsecase

    @Binds
    @Singleton
    abstract fun provideDetailMovieUsecase(detailMovieUsecase: DetailMovieUsecase): IDetailMovieUsecase

}