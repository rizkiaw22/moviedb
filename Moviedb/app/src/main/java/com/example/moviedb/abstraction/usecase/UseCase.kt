package com.example.moviedb.abstraction.usecase

import com.example.moviedb.abstraction.utils.launchCatchError
import kotlinx.coroutines.*


abstract class UseCase<out T : Any>(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    mainDispatchers: CoroutineDispatcher = Dispatchers.Main
) {
    private var parentJob = SupervisorJob()
    private val localScope = CoroutineScope(mainDispatchers + parentJob)

    var params: Any? = null
    var result: Result<Any>? = Fail(Throwable("no result"))

    abstract suspend fun executeOnBackground(): T

    private suspend fun executeCatchError(): Result<T> = withContext(defaultDispatcher){
        try {
            Success(executeOnBackground())

        }catch (throwable:Throwable){
            Fail(throwable)
        }
    }
    fun execute(onSuccess: (T) -> Unit, onError:(Throwable)->Unit) {
        cancelJobs()
        localScope.launchCatchError(block = {
            when (val result = executeCatchError()) {
                is Success -> onSuccess(result.data)
                is Fail -> onError(result.throwable)
            }
        }) {
            if (it !is CancellationException) {
                onError(it)
            }
        }
    }
    fun cancelJobs() {
        localScope.coroutineContext.cancelChildren()
    }
}
