package com.example.moviesubmissionandroidexp.core.domain.upcoming

import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.entities.repository.upcoming.IUpcomingRepository
import com.example.moviesubmissionandroidexp.core.presentation.model.UpcomingModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpcomingMovieUsecase @Inject constructor(
    private val repository: IUpcomingRepository
): IUpcomingMovieUsecase {
    override fun getUpcomingMovie(): Flow<Resource<List<UpcomingModel>>> {
        return repository.getUpcomingMovie()
    }
}