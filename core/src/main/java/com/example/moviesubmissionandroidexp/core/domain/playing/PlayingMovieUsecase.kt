package com.example.moviesubmissionandroidexp.core.domain.playing

import androidx.paging.PagingData
import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.entities.repository.playing.PlayingRepository
import com.example.moviesubmissionandroidexp.core.presentation.model.PlayingNowModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayingMovieUsecase @Inject constructor(private val playingRepository: PlayingRepository): IPlayingMovieUsecase {
    override suspend fun getPlayingMovie(): Flow<PagingData<PlayingNowModel>> {
        return playingRepository.getPlayingMovie()
    }

    override fun getPlayingMovieNotPaging(): Flow<Resource<List<PlayingNowModel>>> {
        return playingRepository.getPlayingNotPaging()
    }
}