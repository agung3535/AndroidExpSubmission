package com.example.moviesubmissionandroidexp.core.entities.repository.favoritemovie

import android.util.Log
import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.entities.source.local.datasource.favoritemovie.IFavoriteMovieLocalDataSource
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.TempDeleteFav
import com.example.moviesubmissionandroidexp.core.presentation.model.CastDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteCategoryWithPreviewModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteDetailMovie
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewDomainModel
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toCastMovieEntity
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toDomain
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toEntity
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toFavDomain
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toReviewFavMovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteMovieRepository @Inject constructor(
    private val localDataSource: IFavoriteMovieLocalDataSource
    ): IFavoriteMovieRepository {
    override fun getFavoriteMovie(favId: Int): Flow<Resource<List<FavoriteMovieModel>>> {
        return flow {
            emit(Resource.Loading())
            val data = localDataSource.getFavMovie(favId)
            if (data.isNotEmpty()) {
                emit(Resource.Success(data.map { it.toDomain() }))
            }else {
                emit(Resource.Success(emptyList()))
            }
        }.flowOn(Dispatchers.IO)
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


    override fun addCategoryFavorite(data: FavoritListCategoryModel): Flow<Long> {
        val insertVal = data.toEntity()
        return localDataSource.addFavCategoryMovie(insertVal)
    }

    override fun deleteFavorite(data: List<FavoriteMovieModel>): Flow<Int> {
        val deleteVal = data.map { it.toEntity() }
        Log.d("dataaa","repo delete invoke $data dan $deleteVal")
        return localDataSource.deleteFavMovie(deleteVal)
    }

    override fun deleteCategoryFavorite(data: FavoritListCategoryModel): Flow<Int> {
        val deleteVal = data.toEntity()
        return localDataSource.deleteCategoryFavMovie(deleteVal)
    }

    override fun getTempFavDelete(): Flow<Resource<List<FavoriteMovieModel>>> {
        return flow {
            emit(Resource.Loading())
            val data = localDataSource.getTempFavDelete()
            if (data.isEmpty()) {
                emit(Resource.Success(emptyList()))
            }else {
                emit(Resource.Success(data.map { it.toFavDomain() }))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun addTempFavDelete(data: TempDeleteFav): Flow<Int> {
        return localDataSource.addTempFavDelete(data)
    }

    override fun deleteTempFavDelete(data: TempDeleteFav): Flow<Int> {
        return localDataSource.deleteTempFavDelete(data)
    }
}