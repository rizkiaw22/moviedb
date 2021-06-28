package com.example.moviedb.repository.api

import com.example.moviedb.BuildConfig
import com.example.moviedb.utils.UserSession
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient<T> constructor(
    private val userSession: UserSession,
    private val service: Class<T>
) {
    private fun retrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .client(getClient())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun call(): T {
        return retrofitBuilder().create(service)
    }

    private fun getClient(): OkHttpClient {
        val httpClients = OkHttpClient().newBuilder()
//        httpClients.addInterceptor(BasicAuthInterceptor(baseUrl))
        if (BuildConfig.DEBUG) {
            httpClients.addInterceptor(loggingInterceptor())
        }
        return httpClients.build()
    }

    private fun loggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}