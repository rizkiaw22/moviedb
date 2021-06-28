package com.example.moviedb.repository.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetMovieNowPlaying(
    @Expose @SerializedName("status") val status: Boolean = false,
    @Expose @SerializedName("code") val code: String = "",
    @Expose @SerializedName("message") val message: String = "",
    @Expose @SerializedName("data") val data: MutableList<eachData>
) {

    data class Request(
        @Expose @SerializedName("api_key") val api_key: String = "",
    )
    data class eachData(
        @Expose @SerializedName("poster_path") val poster_path: String = "",
        @Expose @SerializedName("adult") val adult: Boolean=false,
        @Expose @SerializedName("overview") val overview: String="",
        @Expose @SerializedName("release_date") val release_date: String="",
        @Expose @SerializedName("genre_id") val genre_id: Array<Int>,
        @Expose @SerializedName("id") val id: Int,
        @Expose @SerializedName("original_tittle") val original_tittle:String="",
        @Expose @SerializedName("original_language") val original_language:String="",
        @Expose @SerializedName("tittle") val tittle:String="",
        @Expose @SerializedName("backdrop_path") val backdrop_path:String="",
        @Expose @SerializedName("popularity") val popularity:String="",
        @Expose @SerializedName("vote_count") val vote_count:Int,
        @Expose @SerializedName("video") val video:Boolean=false,
        @Expose @SerializedName("vote_average") val vote_average:Int,
    )
}