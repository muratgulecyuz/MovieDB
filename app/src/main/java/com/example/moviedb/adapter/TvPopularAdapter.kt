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
import com.example.moviedb.model.TvPojo.TvResult
import com.squareup.picasso.Picasso

class TvPopularAdapter (internal var list:MutableList<TvResult>, internal var onTvPopularPositionListener: OnTvPopularPositionListener, internal var context: Context)
    : RecyclerView.Adapter<TvPopularAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.tv_popular_item_layout,parent,false)
        val viewHolder =ViewHolder(view,onTvPopularPositionListener)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+list[position].backdropPath).fit().into(holder.tvPopularIv)
        holder.tvPopularName.text = list[position].name
        var s = list[position].voteAverage.toString()
        var ss1 = SpannableString(s)
        ss1.setSpan(RelativeSizeSpan(1.5f),0,2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        holder.tvPopularRate.text = ss1




    }
    class ViewHolder(itemView:View, internal var onTvPopularPositionListener: OnTvPopularPositionListener):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        internal var tvPopularIv : ImageView
        internal var tvPopularName : TextView
        internal var tvPopularRate : TextView
        init {
            tvPopularIv = itemView.findViewById(R.id.tv_popular_iv)
            tvPopularName = itemView.findViewById(R.id.tv_popular_name_tv)
            tvPopularRate = itemView.findViewById(R.id.mtv_popular_rate_tv)
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onTvPopularPositionListener.onTvPopularClick(adapterPosition)

        }
    }
    interface OnTvPopularPositionListener{
        fun onTvPopularClick(position: Int)
    }
}
