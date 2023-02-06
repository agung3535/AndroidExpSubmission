package com.example.moviesubmissionandroidexp.core.utils

import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.CastFavMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteListCategoryEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteMovieCastReviewEnt
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.FavoriteMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.MovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.OneToManyFavCategoryAndListMovieFavorite
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.ReviewFavMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.TempDeleteFav
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.UpcomingMovieEntity
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.AuthorDetails
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.CastResource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieDetail
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieGenre
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.MovieResource
import com.example.moviesubmissionandroidexp.core.entities.source.remote.response.ReviewResource
import com.example.moviesubmissionandroidexp.core.presentation.model.AuthorDetailsDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.CastDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.CastFavMovieDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.DetailMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteCategoryWithPreviewModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteDetailMovie
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.GenreDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.PlayingNowModel
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewFavMovieDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.UpcomingModel

object Mapper {
    fun MovieEntity.toDomain(): PlayingNowModel {
        return PlayingNowModel(id,title,backdropPath,overview,popularity,voteAverage,originalLanguage)
    }

    fun MovieResource.toEntities(): MovieEntity {
        return MovieEntity(id, title ?: "", backdropPath ?: "", overview ?: "no overview", popularity ?: 0.0, voteAverage ?: 0.0, originalLanguage ?: "")
    }

    fun UpcomingMovieEntity.toDomain(): UpcomingModel {
        return UpcomingModel(id, title, backdropPath, overview, popularity, voteAverage, originalLanguage)
    }

    fun MovieResource.toUpEntities(): UpcomingMovieEntity {
        return UpcomingMovieEntity(id, title ?: "", backdropPath ?: "", overview ?: "no overview", popularity ?: 0.0, voteAverage ?: 0.0, originalLanguage ?: "")
    }

    fun FavoriteMovieEntity.toDomain(): FavoriteMovieModel {
        return FavoriteMovieModel(favId, title, backdropPath, overview, popularity, voteAverage, originalLanguage, favCategoryMovieId, movieId = movieId)
    }

    fun FavoriteListCategoryEntity.toDomain(): FavoritListCategoryModel {
        return FavoritListCategoryModel(favCategoryMovieId, categoryName)
    }

    fun OneToManyFavCategoryAndListMovieFavorite.toDomain(): FavoriteCategoryWithPreviewModel {
        val favData = favCategory.toDomain()
        val favMovie = movie.map { it.toDomain() }
        return FavoriteCategoryWithPreviewModel(favData,favMovie)
    }

    fun CastFavMovieEntity.toDomain(): CastFavMovieDomainModel {
        return CastFavMovieDomainModel(castId, name, originalName, profilePath, fkFavId)
    }

    fun ReviewFavMovieEntity.toDomain(): ReviewFavMovieDomainModel {
        return ReviewFavMovieDomainModel(reviewId, author, AuthorDetailsDomainModel(name = author, username = author,avatarPath,rating.let {
            if (it == null) 0.0 else it
        }), content, createdAt, fkFavId)
    }

    fun FavoriteMovieCastReviewEnt.toDomain(): FavoriteDetailMovie {
        val castData = castMovie.map { it.toDomain() }
        val reviewData = reviewMovie.map { it.toDomain() }
        val favData = favMovie.toDomain()
        return FavoriteDetailMovie(favData,castData,reviewData)
    }

    fun FavoriteMovieModel.toEntity(): FavoriteMovieEntity {
        return FavoriteMovieEntity(favId, title, backdropPath, overview, popularity, voteAverage, originalLanguage, favCategoryMovieId, movieId = movieId)
    }

    fun FavoriteMovieModel.toTempEnt(): TempDeleteFav {
        return TempDeleteFav(favId, title, backdropPath, overview, popularity, voteAverage, originalLanguage, favCategoryMovieId, movieId)
    }

    fun TempDeleteFav.toFavDomain(): FavoriteMovieModel {
        return FavoriteMovieModel(favId, title, backdropPath, overview, popularity, voteAverage, originalLanguage, favCategoryMovieId, movieId)
    }

    fun FavoritListCategoryModel.toEntity(): FavoriteListCategoryEntity {
        return FavoriteListCategoryEntity(favCategoryMovieId, categoryName)
    }

    fun FavoriteCategoryWithPreviewModel.toEntity(): OneToManyFavCategoryAndListMovieFavorite {
        val favData = favCategory.toEntity()
        val favMovie = movie.map { it.toEntity() }
        return OneToManyFavCategoryAndListMovieFavorite(favData,favMovie)
    }

    fun MovieGenre.toDomain(): GenreDomainModel {
        return GenreDomainModel(id, name)
    }

    fun MovieDetail.toDomain(): DetailMovieModel {
        val genreModel = genres.map {
            it.toDomain()
        }
        return DetailMovieModel(id,title,backdropPath,overview,popularity,originalLanguage,voteAverage,genreModel)
    }

    fun CastResource.toDomain(): CastDomainModel {
        return CastDomainModel(adult, gender, id, knowForDepartment, name, originalName, popularity, profilePath, castId, character, creditId)
    }

    fun AuthorDetails.toDomain(): AuthorDetailsDomainModel {
        return AuthorDetailsDomainModel(name.let { if (it == null) "no name" else it }, username.let { if (it == null) "no username" else it }, avatarPath, rating)
    }

    fun ReviewResource.toDomain(): ReviewDomainModel {
        val authorDetails = authorDetail?.toDomain()
        return ReviewDomainModel(author,authorDetails,content, createdAt, id)
    }

    fun CastDomainModel.toCastMovieEntity(fkId: Int): CastFavMovieEntity {
        return CastFavMovieEntity(id.toInt(),name,originalName,profilePath,fkId)
    }

    fun ReviewDomainModel.toReviewFavMovieEntity(fkId: Int): ReviewFavMovieEntity {
        return ReviewFavMovieEntity(id.toInt(),author!!,authorDetail?.avatarPath,authorDetail?.rating,content.let { if (it.isNullOrBlank()) "" else it },
            createdAt.let { if (it.isNullOrBlank()) "" else it },fkId)
    }


}