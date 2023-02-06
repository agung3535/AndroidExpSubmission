package com.example.moviesubmissionandroidexp.core.domain.addfavoritemovie

import android.util.Log
import com.example.moviesubmissionandroidexp.core.entities.repository.favoritemovie.IFavoriteMovieRepository
import com.example.moviesubmissionandroidexp.core.presentation.model.CastDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewDomainModel
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toTempEnt
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddFavoriteMovieUsecase @Inject constructor(private val repository: IFavoriteMovieRepository): IAddFavoriteMovieUsecase {


    override fun addFavorite(
        data: FavoriteMovieModel,
    ): Long {
        return repository.addFavorite(data)
    }

    override fun addCategoryFavorite(data: FavoritListCategoryModel): Flow<Long> {
        return repository.addCategoryFavorite(data)
    }

    override fun addCastFavoriteMovie(data: List<CastDomainModel>, fkId: Int): Flow<List<Long>> {
        return repository.addFavoriteCast(data, fkId)
    }

    override fun addReviewFavoriteMovie(
        data: List<ReviewDomainModel>,
        fkId: Int
    ): Flow<List<Long>> {
        return repository.addFavoriteReview(data, fkId)
    }

    override fun deleteFavorite(data: List<FavoriteMovieModel>): Flow<Int> {
        Log.d("dataaa","delete usecase = ${data}")
        return repository.deleteFavorite(data)
    }

    override fun deleteCategoryFavorite(data: FavoritListCategoryModel): Flow<Int> {
        return repository.deleteCategoryFavorite(data)
    }

    override fun addTempDelete(data: FavoriteMovieModel): Flow<Int> {
        val dataTemp = data.toTempEnt()
        return repository.addTempFavDelete(dataTemp)
    }
}