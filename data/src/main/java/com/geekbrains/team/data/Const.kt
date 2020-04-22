package com.geekbrains.team.data

import java.util.*

object Const {
    val LANGUAGE = Locale.getDefault().toLanguageTag()
    const val BASE_URL = "https://api.themoviedb.org/"

    const val NO_OVERVIEW = "NO DATA"
    const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/w185/"
    const val POSTER_AND_BACKDROP_PREFIX = "https://image.tmdb.org/t/p/w500/"
    const val DATE_FORMAT = "yyyy-MM-dd"
}