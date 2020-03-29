package com.geekbrains.team.filmlibrary.movies.nowPlayingMovies.model

import com.geekbrains.team.domain.movies.nowPlayingMovies.model.NowPlayingMovies
import com.geekbrains.team.domain.movies.upcomingMovies.model.UpcomingMovie

data class NowPlayingMovieView(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val popularity: String,
    val voteCount: String,
    val posterPath: String,
    val backdropPath: String,
    val originalLanguage: String,
    val genreIds: List<Int>,
    val voteAverage: String,
    val overview: String,
    val releaseDate: String
)

fun NowPlayingMovies.toNowPlayingMovieView(): NowPlayingMovieView = NowPlayingMovieView(
    id = id,
    title = title,
    originalTitle = originalTitle,
    popularity = popularity.toString(),
    voteCount = voteCount.toString(),
    posterPath = posterPath,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    genreIds = genreIds,
    voteAverage = voteAverage.toString(),
    overview = overview,
    releaseDate = releaseDate
)