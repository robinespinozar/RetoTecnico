package com.raerossi.retotecnico.data.remote.movie.response

import com.google.gson.annotations.SerializedName
import com.raerossi.retotecnico.data.remote.movie.model.MovieModel

data class MovieResponse(
    @SerializedName("results") val results: List<MovieModel>?,
    @SerializedName("page") val page: Int?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?,
)
