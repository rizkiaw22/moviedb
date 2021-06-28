package com.example.moviedb.repository.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetMovieGenres(
    @Expose @SerializedName("status") val status: Boolean = false,
    @Expose @SerializedName("code") val code: String = "",
    @Expose @SerializedName("message") val message: String = "",
    @Expose @SerializedName("data") val data: MutableList<eachData>
) {

    data class Request(
        @Expose @SerializedName("api_key") val api_key: String = "",
    )
    data class eachData(

        @Expose @SerializedName("id") val id : Int,
        @Expose @SerializedName("name") val name: String = "",
    )
}