package com.example.movieslistinterview.data.models

data class MoviesListresponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)