package com.example.moviesubmissionandroidexp.core.presentation.model

data class ReviewFavMovieDomainModel(

    val reviewId: Int?,

    val author: String?,

    val authorDetail: AuthorDetailsDomainModel?,

    val content: String?,

    val createdAt: String?,

    val fkFavId: Int
)
