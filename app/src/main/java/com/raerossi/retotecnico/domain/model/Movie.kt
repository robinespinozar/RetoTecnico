package com.raerossi.retotecnico.domain.model

data class Movie(
    val title: String = "",
    val poster_path : String = "",
    val vote_average: Double = 0.0,
    val release_date: String = "",
    val overview: String = ""
)
