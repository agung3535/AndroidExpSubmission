package com.example.moviesubmissionandroidexp.di

import com.example.moviesubmissionandroidexp.core.domain.addfavoritemovie.IAddFavoriteMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.detailmovie.IDetailMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.favoritemovie.IFavoriteMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.playing.IPlayingMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.upcoming.IUpcomingMovieUsecase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MovieModules {
    fun playingMovieUsecase(): IPlayingMovieUsecase
    fun upcomingMovieUsecase(): IUpcomingMovieUsecase
    fun detailMovieUsecase(): IDetailMovieUsecase
    fun favoriteMovieUsecase(): IFavoriteMovieUsecase
    fun addFavoriteMovieUsecase(): IAddFavoriteMovieUsecase
}