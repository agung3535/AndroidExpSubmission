package com.example.moviesubmissionandroidexp.core.presentation.model

data class ReviewDomainModel(
    val author: String? = "no name",
    val authorDetail: AuthorDetailsDomainModel?,
    val content: String?,
    val createdAt: String?,
    val id: String
)

data class AuthorDetailsDomainModel (
    val name: String = "no name",
    val username: String = "no name",
    val avatarPath: String?,
    val rating: Double = 0.0
)