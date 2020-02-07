package com.example.moviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.model.CastCrewPojo.Cast
import com.squareup.picasso.Picasso

class CastAdapter (internal var list:MutableList<Cast>, internal var context: Context)
    : RecyclerView.Adapter<CastAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.sub_credit_item_layout,parent,false)
        val viewHolder =ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+list[position].profilePath).fit().into(holder.castIv)
        holder.castNameTv.text = list[position].name
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        internal var castIv : ImageView
        internal var castNameTv : TextView
        init {
            castIv = itemView.findViewById(R.id.cast_iv)
            castNameTv = itemView.findViewById(R.id.cast_name_tv)
        }
    }
}
