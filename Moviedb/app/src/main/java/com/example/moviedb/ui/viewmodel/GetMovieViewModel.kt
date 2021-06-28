package com.example.moviedb.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.abstraction.usecase.Fail
import com.example.moviedb.abstraction.usecase.Result
import com.example.moviedb.abstraction.usecase.Success
import com.example.moviedb.abstraction.utils.launchCatchError
import com.example.moviedb.abstraction.viewmodel.BaseViewModel
import com.example.moviedb.repository.data.GetMovieListDataModel
import com.example.moviedb.repository.usecase.GetMovieUsecase
import com.example.moviedb.utils.UserSession
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMovieViewModel @Inject constructor(
    private val getMovieUsecase:GetMovieUsecase,
    dispatcher: CoroutineDispatcher
):BaseViewModel(dispatcher){
    @Inject
    lateinit var userSession: UserSession

    private val _getMovieResult=MutableLiveData<Result<GetMovieListDataModel>>()
    val getMovieResult : LiveData<Result<GetMovieListDataModel>>
    get() = _getMovieResult

    fun getMove(
        api_key:String
    ){
        launchCatchError(coroutineContext,block = {
            getMovieUsecase.params= mapOf(
                "api_key" to api_key
            )
            getMovieUsecase.execute(
                onSuccess = {
                    _getMovieResult.postValue(Success(it))
                },
                onError ={
                    _getMovieResult.postValue(Fail(it))
                }
            )
        },onError = {
            _getMovieResult.postValue(Fail(it))
        }
        )
    }

    override fun onCleared() {
        super.onCleared()
        getMovieUsecase.cancelJobs()
    }

}




