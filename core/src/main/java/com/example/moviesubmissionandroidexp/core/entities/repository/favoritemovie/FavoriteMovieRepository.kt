package com.example.moviesubmissionandroidexp.core.entities.repository.favoritemovie

import com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.favoritemovie.IFavoriteMovieLocalDataSource
import com.example.moviesubmissionandroidexp.core.presentation.model.CastDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteCategoryWithPreviewModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteDetailMovie
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewDomainModel
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toCastMovieEntity
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toDomain
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toEntity
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toReviewFavMovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteMovieRepository @Inject constructor(private val localDataSource: IFavoriteMovieLocalDataSource): IFavoriteMovieRepository {
    override fun getFavoriteMovie(favId: Int): Flow<List<FavoriteMovieModel>> {
        return localDataSource.getFavMovie(favId).map { data ->
            data.map { it.toDomain() }
        }
    }

    override fun getCategoryFavorite(): Flow<List<FavoriteCategoryWithPreviewModel>> {
        return localDataSource.getFavCategoryMovie().map { data ->
            data.map { it.toDomain() }
        }
    }

    override fun getCategoryList(): Flow<List<FavoritListCategoryModel>> {
        return localDataSource.getCategoryList().map { data ->
            data.map { it.toDomain() }
        }
    }

    override fun getDetailFavorite(movieId: Int): Flow<FavoriteDetailMovie> {
        return localDataSource.getDetailFavMovie(movieId).map { data ->
            data.toDomain()
        }
    }

    override fun cekFavorite(movieId: Int): Flow<List<FavoriteMovieModel>> {
        return localDataSource.cekFavorite(movieId).map { data ->
            data.map { it.toDomain() }
        }
    }

    override fun addFavorite(data: FavoriteMovieModel): Long {
        val insertVal = data.toEntity()
        return localDataSource.addFavMovie(insertVal)
    }

    override fun addFavoriteCast(data: List<CastDomainModel>, fkId: Int): Flow<List<Long>> {
        val insertValue = data.map { it.toCastMovieEntity(fkId) }
        return localDataSource.addFavCastMovie(insertValue)
    }

    override fun addFavoriteReview(data: List<ReviewDomainModel>, fkId: Int): Flow<List<Long>> {
        val inserValue = data.map { it.toReviewFavMovieEntity(fkId) }
        return localDataSource.addFavReviewMovie(inserValue)
    }

//    override fun addFavoriteWithCastReview(
//        data: FavoriteMovieModel,
//        favCast: List<CastDomainModel>,
//        review: List<ReviewDomainModel>
//    ): Flow<Long> {
//        val insertVal = data.toEntity()
//        var castVal = ArrayList<CastFavMovieEntity>()
//        var reviewVal = ArrayList<ReviewFavMovieEntity>()
//        val data = localDataSource.addFavMovie(insertVal)
//        if (data != null) {
//            Log.d("dataaa","masuk tidak null ${data}")
//            GlobalScope.launch {
//                data.collect { favId ->
//                    reviewVal.addAll(review.map {
//                        it.toReviewFavMovieEntity(favId.toInt())
//                    })
//                    castVal.addAll(favCast.map {
//                        it.toCastMovieEntity(favId.toInt())
//                    })
//                }
//            }
//            Log.d("dataaa","masuk cek ${}")
//            if (castVal.size != 0) {
//                Log.d("dataa","masuk cast tidak kosong = ${castVal.size}")
//                localDataSource.addFavCastMovie(castVal)
//            }
//            return localDataSource.addFavReviewMovie(reviewVal)
//        }else {
//            Log.d("dataaa","masuk null ${data}")
//            return flow<Long> {
//                -1L
//            }.flowOn(Dispatchers.IO)
//        }
//
//    }

    override fun addCategoryFavorite(data: FavoritListCategoryModel): Flow<Long> {
        val insertVal = data.toEntity()
        return localDataSource.addFavCategoryMovie(insertVal)
    }

    override fun deleteFavorite(data: List<FavoriteMovieModel>): Flow<Int> {
        val deleteVal = data.map { it.toEntity() }
        return localDataSource.deleteFavMovie(deleteVal)
    }

    override fun deleteCategoryFavorite(data: FavoritListCategoryModel): Flow<Int> {
        val deleteVal = data.toEntity()
        return localDataSource.deleteCategoryFavMovie(deleteVal)
    }
}