package com.example.moviesubmissionandroidexp.core.entities.source.remote.network

import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.CreditMovie
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieDetail
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieResource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieResponse
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.ReviewMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getPlayingMovies(@Query("api_key") apiKey: String, @Query("page") page: Int? = 1): MovieResponse<MovieResource>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponse<MovieResource>

    @GET("movie/upcoming")
    suspend fun getUpComing(@Query("api_key") apiKey: String): MovieResponse<MovieResource>

//    @GET("tv/airing_today")
//    suspend fun getTvToday(@Query("api_key") apiKey: String ): TvResponse<TvResource>
//
//    @GET("tv/popular")
//    suspend fun getPopularTvShow(@Query("api_key") apiKey: String): TvResponse<TvResource>
//
//    @GET("tv/on_the_air")
//    suspend fun getUpcomingUpdateEpisode(@Query("api_key") apiKey: String): TvResponse<TvResource>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movie_id : String,
        @Query("api_key") api_key: String,
    ) : MovieDetail

    @GET("movie/{movie_id}/credits")
    suspend fun getCastMovie(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String
    ): CreditMovie

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String
    ): ReviewMovieResponse

//    @GET("tv/{tv_id}")
//    suspend fun getDetailShow(
//        @Path("tv_id") tv_id : String,
//        @Query("api_key") api_key : String,
//    ) : TvDetailResource
}