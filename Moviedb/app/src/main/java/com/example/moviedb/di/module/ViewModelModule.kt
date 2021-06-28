package com.example.moviedb.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedb.abstraction.viewmodel.ViewModelFactory
import com.example.moviedb.abstraction.viewmodel.ViewModelKey
import com.example.moviedb.di.AppScope
import com.example.moviedb.ui.viewmodel.GetMovieNowPLayingViewModel
import com.example.moviedb.ui.viewmodel.GetMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @AppScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @AppScope
    @Binds
    @IntoMap
    @ViewModelKey(GetMovieViewModel::class)
    internal abstract fun getMovieViewModel(viewModel: GetMovieViewModel): ViewModel

    @AppScope
    @Binds
    @IntoMap
    @ViewModelKey(GetMovieNowPLayingViewModel::class)
    internal abstract fun getMovieNpViewModel(viewModel: GetMovieNowPLayingViewModel): ViewModel
}
