package com.raerossi.retotecnico.di

import android.content.Context
import androidx.room.Room
import com.raerossi.retotecnico.data.local.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val MOVIE_DATA_BASE = "movie_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): MovieDataBase {
        return Room.databaseBuilder(context, MovieDataBase::class.java, MOVIE_DATA_BASE)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDataBase) = database.getMovieDao()

}