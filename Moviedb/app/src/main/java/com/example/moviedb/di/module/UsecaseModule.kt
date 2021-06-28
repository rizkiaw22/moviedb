package com.example.moviedb.di.module

import com.example.moviedb.di.AppScope
import com.example.moviedb.repository.api.Api
import com.example.moviedb.repository.api.ApiClient
import com.example.moviedb.repository.data.GetMovieNowPlaying
import com.example.moviedb.repository.usecase.GetMoviNowPlayingUsecase
import com.example.moviedb.repository.usecase.GetMovieUsecase
import dagger.Module
import dagger.Provides

@Module
class UsecaseModule {
    @AppScope
    @Provides
    fun provideGetMovieViewModel(apiClient: ApiClient<Api>): GetMovieUsecase {
        return GetMovieUsecase(apiClient)
    }

    @AppScope
    @Provides
    fun provideGetMovieNowPlay(apiClient: ApiClient<Api>): GetMoviNowPlayingUsecase {
        return GetMoviNowPlayingUsecase(apiClient)
    }

}