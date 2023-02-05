package com.example.moviesubmissionandroidexp.core.entities.repository.playing

import androidx.paging.PagingData
import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.presentation.model.PlayingNowModel
import kotlinx.coroutines.flow.Flow

interface IPlayingRepository {
    suspend fun getPlayingMovie(): Flow<PagingData<PlayingNowModel>>
    fun getPlayingNotPaging(): Flow<Resource<List<PlayingNowModel>>>
}