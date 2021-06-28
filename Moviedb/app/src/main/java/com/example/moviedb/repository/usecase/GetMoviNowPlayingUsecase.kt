package com.example.moviedb.repository.usecase

import com.example.moviedb.abstraction.usecase.UseCase
import com.example.moviedb.repository.api.Api
import com.example.moviedb.repository.api.ApiClient
import com.example.moviedb.repository.data.GetMovieListDataModel
import com.example.moviedb.repository.data.GetMovieNowPlaying

class GetMoviNowPlayingUsecase(
    private val api: ApiClient<Api>
): UseCase<GetMovieNowPlaying>() {
    override suspend fun executeOnBackground(): GetMovieNowPlaying {
        return api.call().getMovieNowPlay(params as HashMap<String, String>)
    }
}