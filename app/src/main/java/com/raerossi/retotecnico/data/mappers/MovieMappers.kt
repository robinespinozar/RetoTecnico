package com.raerossi.retotecnico.data.mappers

import com.raerossi.retotecnico.data.local.entities.MovieEntity
import com.raerossi.retotecnico.data.remote.movie.model.MovieModel
import com.raerossi.retotecnico.domain.model.Movie

fun MovieModel.toDomain() = Movie(
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    overview = overview
)

fun Movie.toDatabase() = MovieEntity(
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    overview = overview
)

fun MovieModel.toDatabase() = MovieEntity(
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    overview = overview
)

fun MovieEntity.toDomain() = Movie(
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    overview = overview
)