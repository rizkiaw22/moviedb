package com.example.moviedb.abstraction.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun CoroutineScope.launchCatchError(
    context: CoroutineContext = coroutineContext,
    block: suspend (() -> Unit),
    onError: suspend (Throwable) -> Unit
) = launch(context) {
    try {
        block()
    } catch (e: Exception) {
        onError(Throwable(e.message))
    }
}