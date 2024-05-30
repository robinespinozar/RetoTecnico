package com.raerossi.retotecnico.data.remote.movie.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.raerossi.retotecnico.data.local.MovieDataBase
import com.raerossi.retotecnico.data.local.dao.MovieDao
import com.raerossi.retotecnico.data.local.entities.MovieEntity
import com.raerossi.retotecnico.data.mappers.toDatabase
import com.raerossi.retotecnico.data.mappers.toDomain
import com.raerossi.retotecnico.data.remote.movie.MovieService
import com.raerossi.retotecnico.domain.model.Movie
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val api: MovieService
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val movies = api.getMovies(page = currentPage)

            LoadResult.Page(
                data = movies.results!!.map { it.toDomain() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.results.isEmpty()) null else movies.page!! + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}