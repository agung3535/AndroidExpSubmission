package com.example.moviesubmissionandroidexp.core.entities.source.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewResource(
    @SerializedName("author") val author: String?,
    @SerializedName("author_details") val authorDetail: AuthorDetails?,
    @SerializedName("content") val content: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("id") val id: String
)

data class AuthorDetails (
    val name: String?,
    val username: String?,
    @SerializedName("avatar_path") val avatarPath: String?,
    val rating: Double = 0.0
)