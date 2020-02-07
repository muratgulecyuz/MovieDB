package com.example.moviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.model.MoviesPojo.MovieResult
import com.squareup.picasso.Picasso

class TopRatedMoviesAdapter (internal var list:MutableList<MovieResult>, internal var topRatedPositionListener:OnTopRatedPositionListener, internal var context: Context)
    : RecyclerView.Adapter<TopRatedMoviesAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.movie_top_rated_movies_item_layout,parent,false)
        val viewHolder =ViewHolder(view,topRatedPositionListener)
        return viewHolder
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+list[position].backdropPath).into(holder.movieIv)



    }
    class ViewHolder(itemView:View,internal var onTopRatedPositionListener:OnTopRatedPositionListener):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        internal var movieIv : ImageView
        init {
            movieIv = itemView.findViewById(R.id.movieIv)

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            onTopRatedPositionListener.onTopRatedClick(adapterPosition)
        }
    }
    interface OnTopRatedPositionListener{
        fun onTopRatedClick(position: Int)
    }
}
