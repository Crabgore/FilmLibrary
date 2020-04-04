package com.geekbrains.team.data.movies.upcomingMovies.repository

import com.geekbrains.team.data.Const.LANGUAGE
import com.geekbrains.team.data.Key.API_KEY
import com.geekbrains.team.data.movies.upcomingMovies.service.UpcomingMoviesApi
import com.geekbrains.team.data.movies.upcomingMovies.service.model.toMovie
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.upcomingMovies.repository.UpcomingMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class UpcomingMoviesRepositoryImpl @Inject constructor(private val api: UpcomingMoviesApi) :
    UpcomingMoviesRepository {

    override fun fetch(page: Int): Single<List<Movie>> =
        api.getUpcomingMovies(apiKey = API_KEY, language = LANGUAGE, page = page)
            .map { response ->
                response.toMovie()
            }
}