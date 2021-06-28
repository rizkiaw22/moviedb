package com.example.moviedb.abstraction.usecase

sealed class Result<out T : Any>
data class Success<out T : Any>(val data: T) : Result<T>()
data class Fail(val throwable: Throwable) : Result<Nothing>()