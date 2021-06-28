package com.example.moviedb.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.abstraction.adapter.BaseAdapter
import com.example.moviedb.databinding.ItemMovieBinding
import com.example.moviedb.repository.data.GetMovieListDataModel
import com.example.moviedb.utils.ImageUtils

class FragmentMovieAdapter(
    private val context:Context,
    private val clickListener:Listener,
    private val itemsMovie:MutableList<GetMovieListDataModel.eachData>

): RecyclerView.Adapter<FragmentMovieAdapter.ViewHolder>(){

    class ViewHolder(val binding:ItemMovieBinding,val context: Context): RecyclerView.ViewHolder(binding.root){
        fun bind(listItem:GetMovieListDataModel.eachData,clickListener:Listener){
            listItem.let {
                if(it.original_tittle!!.isEmpty()){
                    binding.movieTitle.visibility= View.VISIBLE
                    binding.movieDesc.visibility= View.GONE
                }else{
                    binding.ratingBar.visibility= View.GONE
                    binding.imgPhoto.visibility= View.VISIBLE
                    ImageUtils.loadImage(context,binding.imgPhoto,listItem.poster_path)
                }
                binding.movieTitle.text=listItem.original_tittle
                binding.movieDesc.text=listItem.overview
                binding.ratingBar.text=listItem.vote_average.toString()
                ImageUtils.loadImage(context,binding.imgPhoto,listItem.poster_path)


            }
            binding.root.setOnClickListener {
                clickListener.onClick(listItem)
            }
        }
    }

    interface Listener{
        fun onClick(items: GetMovieListDataModel.eachData)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding,context)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val singleItem:GetMovieListDataModel.eachData=itemsMovie[position]
        holder.bind(singleItem,clickListener)
    }
    override fun getItemCount(): Int =itemsMovie.count()
}

