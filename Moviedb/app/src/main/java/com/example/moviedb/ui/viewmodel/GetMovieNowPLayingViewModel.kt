package com.example.moviedb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.abstraction.usecase.Fail
import com.example.moviedb.abstraction.usecase.Result
import com.example.moviedb.abstraction.usecase.Success
import com.example.moviedb.abstraction.utils.launchCatchError
import com.example.moviedb.abstraction.viewmodel.BaseViewModel
import com.example.moviedb.repository.data.GetMovieListDataModel
import com.example.moviedb.repository.data.GetMovieNowPlaying
import com.example.moviedb.repository.usecase.GetMoviNowPlayingUsecase
import com.example.moviedb.repository.usecase.GetMovieUsecase
import com.example.moviedb.utils.UserSession
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMovieNowPLayingViewModel @Inject constructor(
    private val getMovieNowPlayingUsecase: GetMoviNowPlayingUsecase,
    dispatcher: CoroutineDispatcher
): BaseViewModel(dispatcher){
    @Inject
    lateinit var userSession: UserSession

    private val _getMovieNpResult= MutableLiveData<Result<GetMovieNowPlaying>>()
    val getMovieNpResult : LiveData<Result<GetMovieNowPlaying>>
        get() = _getMovieNpResult

    fun getMove(
        api_key:String
    ){
        launchCatchError(coroutineContext,block = {
            getMovieNowPlayingUsecase.params= mapOf(
                "api_key" to api_key
            )
            getMovieNowPlayingUsecase.execute(
                onSuccess = {
                    _getMovieNpResult.postValue(Success(it))
                },
                onError ={
                    _getMovieNpResult.postValue(Fail(it))
                }
            )
        },onError = {
            _getMovieNpResult.postValue(Fail(it))
        }
        )
    }

    override fun onCleared() {
        super.onCleared()
        getMovieNowPlayingUsecase.cancelJobs()
    }

}