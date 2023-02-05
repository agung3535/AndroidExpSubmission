package com.example.moviesubmissionandroidexp.core.entities.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieplaying_remote_keys")
data class MoviePlayingRemoteKeys(
    @PrimaryKey val id: String,
    val prevKey: Int?,
    val nextKey: Int?,
)