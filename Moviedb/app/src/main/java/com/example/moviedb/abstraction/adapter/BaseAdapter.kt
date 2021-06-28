package com.example.moviedb.abstraction.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter: RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    protected abstract fun layout(): Int
    protected abstract fun onBind(holder: ViewHolder, position: Int)
    protected abstract fun count(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(layout(), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        onBind(holder, position)
    }

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)
}