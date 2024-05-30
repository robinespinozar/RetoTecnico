package com.raerossi.retotecnico.domain.model

import com.raerossi.retotecnico.data.remote.movie.model.MovieModel
import com.raerossi.retotecnico.utils.Constants

data class Movie(
    val title: String = "",
    val posterPath : String? = "",
    val voteAverage: Double = 0.0,
    val releaseDate: String = "",
    val overview: String = ""
){
    fun getImageUrl() = Constants.URL_IMAGE + this.posterPath

    fun getRealeaseDateFormat() = "( $releaseDate )"
}
