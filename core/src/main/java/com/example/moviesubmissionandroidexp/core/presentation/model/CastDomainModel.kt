package com.example.moviesubmissionandroidexp.core.presentation.model

data class CastDomainModel(
    val adult: Boolean,
    val gender: Long,
    val id: Long,
    val knowForDepartment: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String? = null,
    val castId: Long? = null,
    val character: String? = null,
    val creditId: String,
    val order: Long? = null,
    val department: String? = null,
    val job: String? = null
)
