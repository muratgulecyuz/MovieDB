package com.example.moviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.model.TvPojo.TvResult
import com.squareup.picasso.Picasso

class TvAdapter (internal var list:MutableList<TvResult>, internal var onTvPositionListener: OnTvPositionListener, internal var context: Context)
    : RecyclerView.Adapter<TvAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.tv_item_layout,parent,false)
        val viewHolder =ViewHolder(view,onTvPositionListener)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+list[position].posterPath).fit().into(holder.movieIv)
        holder.movieName.text = list[position].name




    }
    class ViewHolder(itemView:View, internal var onTvPositionListener: OnTvPositionListener):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        internal var movieIv : ImageView
        internal var movieName : TextView
        init {
            movieIv = itemView.findViewById(R.id.movie_iv)
            movieName = itemView.findViewById(R.id.movie_name_tv)
            itemView.setOnClickListener(this)

        }
        override fun onClick(p0: View?) {
            onTvPositionListener.onTvClick(adapterPosition)

        }
    }
    interface OnTvPositionListener{
        fun onTvClick(position: Int)
    }
}
