package com.example.moviesubmissionandroidexp.core.entities.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesubmissionandroidexp.core.entities.source.local.entity.MoviePlayingRemoteKeys

@Dao
interface MoviePlayingRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(remoteKeys: List<MoviePlayingRemoteKeys>)

    @Query("SELECT * FROM movieplaying_remote_keys WHERE id = :id")
    suspend fun getRemoteKeysId(id: String): MoviePlayingRemoteKeys?

    @Query("DELETE FROM movieplaying_remote_keys")
    suspend fun deleteAll()
}