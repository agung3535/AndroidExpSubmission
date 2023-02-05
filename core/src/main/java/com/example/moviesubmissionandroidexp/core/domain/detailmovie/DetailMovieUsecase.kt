package com.example.moviesubmissionandroidexp.core.domain.detailmovie

import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.entities.repository.detailmovie.IDetailMovieRepository
import com.example.moviesubmissionandroidexp.core.presentation.model.CastDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.DetailMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailMovieUsecase @Inject constructor(
    private val repository: IDetailMovieRepository
): IDetailMovieUsecase {
    override fun getDetailMovie(movieId: String): Flow<Resource<DetailMovieModel>> {
        return repository.getDetailMovie(movieId)
    }

    override fun getCastMovie(movieId: String): Flow<Resource<List<CastDomainModel>>> {
        return repository.getCastMovie(movieId)
    }

    override fun getReviewMovie(movieId: String): Flow<Resource<List<ReviewDomainModel>>> {
        return repository.getReview(movieId)
    }
}