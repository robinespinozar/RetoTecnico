package com.raerossi.retotecnico.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.raerossi.retotecnico.data.local.Tables
import com.raerossi.retotecnico.data.local.entities.MovieEntity

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM " + Tables.MOVIE)
    fun pagingSource(): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM " + Tables.MOVIE)
    suspend fun clearAll()
}