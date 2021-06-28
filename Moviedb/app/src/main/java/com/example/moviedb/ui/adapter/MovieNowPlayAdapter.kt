package com.example.moviedb.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.databinding.ItemMovieHorizontalBinding
import com.example.moviedb.repository.data.GetMovieNowPlaying
import com.example.moviedb.utils.ImageUtils

abstract  class MovieNowPlayAdapter (
    private val context: Context,
    private val clickListener:Listener,
    private val itemsMovieNowPlaying:MutableList<GetMovieNowPlaying.eachData>

        ):RecyclerView.Adapter<MovieNowPlayAdapter.ViewHolder>(){
            class ViewHolder(val binding: ItemMovieHorizontalBinding,val context: Context):RecyclerView.ViewHolder(binding.root){
                fun bind(listItem: GetMovieNowPlaying.eachData, clickListener: MovieNowPlayAdapter.Listener){
                    listItem.let {
                        ImageUtils.loadImage(context,binding.imgMovie,listItem.poster_path)

                    }
                   binding.root.setOnClickListener {
                       clickListener.onClick(listItem)
                   }
                }
            }
    interface Listener{
        fun onClick(items: GetMovieNowPlaying.eachData)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieNowPlayAdapter.ViewHolder {
        val binding= ItemMovieHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieNowPlayAdapter.ViewHolder(binding, context)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val singleItem:GetMovieNowPlaying.eachData=itemsMovieNowPlaying[position]
        holder.bind(singleItem,clickListener)
    }
    override fun getItemCount(): Int =itemsMovieNowPlaying.count()
}