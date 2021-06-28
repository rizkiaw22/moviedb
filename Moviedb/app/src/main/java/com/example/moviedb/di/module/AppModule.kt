package com.example.moviedb.di.module

import android.content.Context
import com.example.moviedb.di.AppScope
import com.example.moviedb.repository.api.Api
import com.example.moviedb.repository.api.ApiClient
import com.example.moviedb.utils.UserSession
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class AppModule constructor(private val context: Context){

    @AppScope
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @AppScope
    @Provides
    fun provideUserSession(): UserSession {
        return UserSession(context)
    }

    @AppScope
    @Provides
    fun provideApiClient(userSession: UserSession): ApiClient<Api> {
        return ApiClient(userSession,Api::class.java)
    }

}