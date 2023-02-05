package com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.favoritemovie

import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.CastFavMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteListCategoryEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteMovieCastReviewEnt
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.OneToManyFavCategoryAndListMovieFavorite
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.ReviewFavMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.room.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteMovieLocalDataSource @Inject constructor(private val movieDao: MovieDao): IFavoriteMovieLocalDataSource {
    override fun getFavMovie(favId: Int): Flow<List<FavoriteMovieEntity>> {
        return movieDao.getFavoriteMovie(favId)
    }

    override fun getFavCategoryMovie(): Flow<List<OneToManyFavCategoryAndListMovieFavorite>> {
        return movieDao.getAllCategoryFavList()
    }

    override fun getCategoryList(): Flow<List<FavoriteListCategoryEntity>> {
        return movieDao.getCategoryList()
    }

    override fun getDetailFavMovie(movieId: Int): Flow<FavoriteMovieCastReviewEnt> {
        return movieDao.getFavoriteMovieDetail(movieId)
    }

    override fun cekFavorite(movieId: Int): Flow<List<FavoriteMovieEntity>> {
        return movieDao.cekFavorite(movieId)
    }

    override fun addFavCategoryMovie(data: FavoriteListCategoryEntity): Flow<Long> {
        return flow<Long> {
            movieDao.createCategory(data)
        }.flowOn(Dispatchers.IO)
    }

    override fun addFavMovie(data: FavoriteMovieEntity): Long {
        return movieDao.addFavMovie(data)
    }

    override fun addFavCastMovie(data: List<CastFavMovieEntity>): Flow<List<Long>> {
        return flow<List<Long>> {
            movieDao.addFavCastMovie(data)
        }.flowOn(Dispatchers.IO)
    }

    override fun addFavReviewMovie(data: List<ReviewFavMovieEntity>): Flow<List<Long>> {
        return flow<List<Long>> {
            movieDao.addFavReviewMovie(data)
        }.flowOn(Dispatchers.IO)
    }

    override fun deleteFavMovie(data: List<FavoriteMovieEntity>): Flow<Int> {
        return flow<Int> {
            movieDao.deleteFavMovie(data)
        }.flowOn(Dispatchers.IO)
    }

    override fun deleteCategoryFavMovie(data: FavoriteListCategoryEntity): Flow<Int> {
        return flow<Int> {
            movieDao.deleteCategory(data)
        }.flowOn(Dispatchers.IO)
    }
}