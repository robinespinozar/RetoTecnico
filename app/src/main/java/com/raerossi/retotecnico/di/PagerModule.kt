package com.raerossi.retotecnico.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.raerossi.retotecnico.data.local.MovieDataBase
import com.raerossi.retotecnico.data.local.dao.MovieDao
import com.raerossi.retotecnico.data.local.entities.MovieEntity
import com.raerossi.retotecnico.data.remote.movie.MovieService
import com.raerossi.retotecnico.data.remote.movie.mediator.MovieRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagerModule {

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideMoviePager(
        db: MovieDataBase,
        dao: MovieDao,
        api: MovieService
    ): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            remoteMediator = MovieRemoteMediator(
                movieDb = db,
                dao = dao,
                movieApi = api
            ),
            pagingSourceFactory = {
                dao.pagingSource()
            }
        )
    }
}