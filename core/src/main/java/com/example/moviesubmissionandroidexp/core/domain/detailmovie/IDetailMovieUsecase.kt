package com.example.moviesubmissionandroidexp.core.domain.detailmovie

import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.presentation.model.CastDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.DetailMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewDomainModel
import kotlinx.coroutines.flow.Flow

interface IDetailMovieUsecase {
    fun getDetailMovie(movieId: String): Flow<Resource<DetailMovieModel>>
    fun getCastMovie(movieId: String): Flow<Resource<List<CastDomainModel>>>
    fun getReviewMovie(movieId: String): Flow<Resource<List<ReviewDomainModel>>>
}