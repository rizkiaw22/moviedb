package com.example.moviedb.di

import com.example.moviedb.di.module.AppModule
import com.example.moviedb.di.module.UsecaseModule
import com.example.moviedb.di.module.ViewModelModule
import com.example.moviedb.ui.activity.HomeActivity
import dagger.Component

@AppScope
@Component(modules = [
    AppModule::class,
    ViewModelModule::class,
    UsecaseModule::class
])
interface AppComponent {
    fun inject(activity: HomeActivity)
}