package com.example.moviedb.abstraction.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.isActive
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    private val dispatcher: CoroutineDispatcher
): ViewModel(), CoroutineScope {
    private val masterJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = dispatcher + masterJob

    open fun flush(){
        if (isActive && !masterJob.isCancelled){
            masterJob.children.map { it.cancel() }
        }
    }

    override fun onCleared() {
        super.onCleared()
        flush()
    }
}