package com.example.moviesubmissionandroidexp.di

import com.example.moviesubmissionandroidexp.core.domain.addfavoritemovie.IAddFavoriteMovieUsecase
import com.example.moviesubmissionandroidexp.core.domain.favoritemovie.IFavoriteMovieUsecase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModules {

    fun favoriteMovieUsecase(): IFavoriteMovieUsecase
    fun addFavoriteMovieUsecase(): IAddFavoriteMovieUsecase


}