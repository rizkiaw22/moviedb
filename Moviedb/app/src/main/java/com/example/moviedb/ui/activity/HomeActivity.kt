package com.example.moviedb.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviedb.abstraction.activity.BaseDaggerActivity
import com.example.moviedb.abstraction.usecase.Fail
import com.example.moviedb.abstraction.usecase.Success
import com.example.moviedb.abstraction.viewmodel.ViewModelFactory
import com.example.moviedb.databinding.FragmentMovieBinding
import com.example.moviedb.databinding.LayoutHomeActivityBinding
import com.example.moviedb.di.DaggerAppComponent
import com.example.moviedb.di.module.AppModule
import com.example.moviedb.repository.data.GetMovieListDataModel
import com.example.moviedb.repository.data.GetMovieNowPlaying
import com.example.moviedb.ui.adapter.FragmentMovieAdapter
import com.example.moviedb.ui.adapter.MovieNowPlayAdapter
import com.example.moviedb.ui.viewmodel.GetMovieNowPLayingViewModel
import com.example.moviedb.ui.viewmodel.GetMovieViewModel
import com.gauravk.bubblenavigation.BubbleNavigationLinearView
import java.io.Serializable
import javax.inject.Inject

class HomeActivity: BaseDaggerActivity<LayoutHomeActivityBinding>(), FragmentMovieAdapter.Listener{
    @Inject
    lateinit var viewmodelFactory: ViewModelFactory
    private val viewmodelProvider by lazy { ViewModelProvider(this,viewmodelFactory) }
    private val viewmodel by lazy { viewmodelProvider.get(GetMovieViewModel::class.java) }
    private val nowPlayViewmodel by lazy { viewmodelProvider.get(GetMovieNowPLayingViewModel::class.java) }

    private var itemMovie:MutableList<GetMovieListDataModel.eachData> = mutableListOf()
    private var itemMovieNp:MutableList<GetMovieNowPlaying.eachData> = mutableListOf()
    private var adapter: FragmentMovieAdapter?=null
    private var NPadapter: MovieNowPlayAdapter?=null

    override fun getViewBinding()= LayoutHomeActivityBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserverMovie()
        loadDataMovie()
        initViewMovie()
    }
    override fun initInjector() {
        DaggerAppComponent.builder().appModule(AppModule(this))
            .build()
            .inject(this)
    }
    private fun initObserverMovie(){
        viewmodel.getMovieResult.observe(this, Observer {
            when(it){
                is Success ->{
                    if (it.data.status){
                        if (it.data.data.isNotEmpty()){
                            itemMovie.clear()
                            itemMovie.addAll(it.data.data)
                            adapter?.notifyDataSetChanged()

                        }

                    }
                }
                is Fail ->{
                    onError(it.throwable)
                }
            } })



    }
    private fun initObserverMovieNp(){
        nowPlayViewmodel.getMovieNpResult.observe(this, Observer {
            when(it){
                is Success ->{
                    if (it.data.status){
                        if (it.data.data.isNotEmpty()){
                            itemMovie.clear()
                            itemMovie.addAll(it.data.data)
                            adapter?.notifyDataSetChanged()

                        }

                    }
                }
                is Fail ->{
                    onErrorNp(it.throwable)
                }
            } })



    }
    private fun initViewMovie(){
        adapter= FragmentMovieAdapter(this,this,itemMovie)
        binding.movieContent.movieRecomendRV.adapter=adapter
    }
    private fun initViewMovieNp(){
        NPadapter= MovieNowPlayAdapter(this,this,itemMovie)
    }
    private fun onError(throwable: Throwable){
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
    private fun onErrorNp(throwable: Throwable){
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
    private fun loadDataMovie(){
        viewmodel.getMove(api_key = "859e1e2595ca61e03a724fb8889e0ddb")
    }
    private fun loadDataMovieNp(){
        nowPlayViewmodel.getMove(api_key = "859e1e2595ca61e03a724fb8889e0ddb")
    }
    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        loadDataMovie()
    }

    override fun onClick(items: GetMovieListDataModel.eachData) {
        val intent= Intent(this,DetailMovieActivity::class.java)
        intent.let{
        }
        openIntent(intent)
    }

    override fun fragment(): Fragment? {
        return null
    }




}