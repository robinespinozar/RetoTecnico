package com.raerossi.retotecnico.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raerossi.retotecnico.data.local.dao.MovieDao
import com.raerossi.retotecnico.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
}