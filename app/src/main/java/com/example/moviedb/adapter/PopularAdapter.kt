package com.example.moviedb.adapter

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.model.MoviesPojo.MovieResult
import com.squareup.picasso.Picasso

class PopularAdapter(internal var list:MutableList<MovieResult>, internal var onPopularPositionListener: OnPopularPositionListener, internal var context: Context)
    : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.movie_popular_item_layout,parent,false)
        val viewHolder =ViewHolder(view,onPopularPositionListener)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+list[position].posterPath).fit().into(holder.movieIv)
        holder.movieNameTv.text = list[position].title
        var s = list[position].voteAverage.toString()
        var ss1 = SpannableString(s)
        ss1.setSpan(RelativeSizeSpan(1.5f),0,2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        holder.movieRateTv.text = ss1




    }
    class ViewHolder(itemView:View,internal var onPopularPositionListener: OnPopularPositionListener):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        internal var movieIv : ImageView
        internal var movieNameTv : TextView
        internal var movieRateTv : TextView
        init {
            movieIv = itemView.findViewById(R.id.movie_iv)
            movieNameTv = itemView.findViewById(R.id.movie_name_tv)
            movieRateTv = itemView.findViewById(R.id.movie_rate_tv)
            itemView.setOnClickListener(this)

        }
        override fun onClick(p0: View?) {
            onPopularPositionListener.onPopularClick(adapterPosition)

        }
    }
    interface OnPopularPositionListener{
        fun onPopularClick(position: Int)
    }
}
