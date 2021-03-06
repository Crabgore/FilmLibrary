package com.geekbrains.team.domain.movies.searchMovies.repository

import com.geekbrains.team.domain.movies.model.Movie
import io.reactivex.Single

interface SearchMoviesRepository {
    fun fetch(query: String, releaseYear: Int, page: Int): Single<List<Movie>>
}