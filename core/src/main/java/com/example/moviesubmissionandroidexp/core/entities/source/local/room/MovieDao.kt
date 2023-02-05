package com.example.moviesubmissionandroidexp.core.entities.source.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.CastFavMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteListCategoryEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteMovieCastReviewEnt
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.MovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.OneToManyFavCategoryAndListMovieFavorite
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.ReviewFavMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM playingnow")
    fun getMovie(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM playingnow")
    fun getMovieNotPaging(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM upcoming_movie")
    fun getUpcomingMovie(): Flow<List<UpcomingMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPlayingMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUpcomingMovie(movie: List<UpcomingMovieEntity>)

    @Query("DELETE FROM playingnow")
    suspend fun deleteAllPlaying()

    @Query("DELETE FROM upcoming_movie")
    suspend fun deleteAllUpcoming()

    //one to many fav category & fav movie
    @Transaction
    @Query("SELECT * FROM favorite_category")
    fun getAllCategoryFavList(): Flow<List<OneToManyFavCategoryAndListMovieFavorite>>

    //one to one
    @Transaction
    @Query("SELECT * FROM favorite_movie WHERE movie_id = :movieId")
    fun getFavoriteMovieDetail(movieId: Int): Flow<FavoriteMovieCastReviewEnt>

    @Query("SELECT * FROM favorite_category")
    fun getCategoryList(): Flow<List<FavoriteListCategoryEntity>>

    @Query("SELECT * FROM favorite_movie WHERE fav_category_id = :categoryId ")
    fun getFavoriteMovie(categoryId: Int): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM favorite_movie WHERE movie_id = :movieId ")
    fun cekFavorite(movieId: Int): Flow<List<FavoriteMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createCategory(data: FavoriteListCategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavMovie(data: FavoriteMovieEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavCastMovie(data: List<CastFavMovieEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavReviewMovie(data: List<ReviewFavMovieEntity>)

    @Delete
    fun deleteFavMovie(favMovie: List<FavoriteMovieEntity>): Int

    @Delete
    fun deleteCategory(categoryEntity: FavoriteListCategoryEntity): Int

}