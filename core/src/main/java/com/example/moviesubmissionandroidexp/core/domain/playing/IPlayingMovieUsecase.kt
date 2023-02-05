package com.example.moviesubmissionandroidexp.core.domain.playing

import androidx.paging.PagingData
import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.presentation.model.PlayingNowModel
import kotlinx.coroutines.flow.Flow

interface IPlayingMovieUsecase {
    suspend fun getPlayingMovie(): Flow<PagingData<PlayingNowModel>>
    fun getPlayingMovieNotPaging(): Flow<Resource<List<PlayingNowModel>>>
}