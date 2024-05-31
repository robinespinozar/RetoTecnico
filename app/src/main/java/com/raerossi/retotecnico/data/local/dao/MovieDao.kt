package com.raerossi.retotecnico.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.raerossi.retotecnico.data.local.Tables
import com.raerossi.retotecnico.data.local.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("Select * from " + Tables.MOVIE)
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(listMovies: List<MovieEntity>)

    @Query("Delete from " + Tables.MOVIE)
    suspend fun clearAllMovies()
}