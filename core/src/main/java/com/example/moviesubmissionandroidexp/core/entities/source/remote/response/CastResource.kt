package com.example.moviesubmissionandroidexp.core.entities.source.remote.response

import com.google.gson.annotations.SerializedName

data class CastResource(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("gender") val gender: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("known_for_department") val knowForDepartment: String,
    @SerializedName("name") val name: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("profile_path") val profilePath: String? = null,
    @SerializedName("cast_id") val castId: Long? = null,
    @SerializedName("character") val character: String? = null,
    @SerializedName("credit_id") val creditId: String,
    @SerializedName("order") val order: Long? = null,
    @SerializedName("department") val department: String? = null,
    @SerializedName("jon") val job: String? = null

)