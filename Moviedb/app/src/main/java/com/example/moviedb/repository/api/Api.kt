package com.example.moviedb.repository.api

import com.example.moviedb.repository.data.GetMovieGenres
import com.example.moviedb.repository.data.GetMovieListDataModel
import com.example.moviedb.repository.data.GetMovieNowPlaying
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("movie/popular")
    suspend fun getMovieList(
        @FieldMap params: Map<String, String>
    ): GetMovieListDataModel

    @GET("movie/now_playing")
    suspend fun getMovieNowPlay(
        @FieldMap params: Map<String, String>
    ): GetMovieNowPlaying

    @GET("genre/movie/list")
    suspend fun genreMovieList(
        @FieldMap params: Map<String, String>
    ): GetMovieGenres
}