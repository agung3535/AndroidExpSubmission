package com.example.moviesubmissionandroidexp.movie.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesubmissionandroidexp.core.domain.detailmovie.IDetailMovieUsecase
import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.presentation.model.CastDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.DetailMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewDomainModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@ViewModelScoped
class DetailMovieViewmodel(private val detailMovieUsecase: IDetailMovieUsecase): ViewModel() {

    val movieDetail: MutableStateFlow<Resource<DetailMovieModel>?> = MutableStateFlow(null)
    val castList: MutableStateFlow<Resource<List<CastDomainModel>>?> = MutableStateFlow(null)
    val reviewMovie: MutableStateFlow<Resource<List<ReviewDomainModel>>?> = MutableStateFlow(null)

    fun getMovieDetail(movieId: String) = viewModelScope.launch {

        detailMovieUsecase.getDetailMovie(movieId).collect { collectData ->
            movieDetail.value = collectData
        }
    }

    fun getCastMovie(movieId: String) = viewModelScope.launch {
        detailMovieUsecase.getCastMovie(movieId).collect { collectData ->

            castList.value = collectData
        }
    }

    fun getReviewMovie(movieId: String) = viewModelScope.launch {
        detailMovieUsecase.getReviewMovie(movieId).collect { collectData ->
            Log.d("dataaaa","call viewmodel ${movieId}= ${collectData.data}")
            reviewMovie.value = collectData
        }
    }

}