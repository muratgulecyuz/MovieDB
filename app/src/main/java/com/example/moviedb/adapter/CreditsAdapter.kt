package com.example.moviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.model.CastCrewPojo.CreditsPojo

class CreditsAdapter (internal var creditItem:CreditsPojo, internal var context: Context)
    : RecyclerView.Adapter<CreditsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.credit_item_layout,parent,false)
        val viewHolder =ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fixRecyclerView(holder.castAndCrewRecyclerView)
        if (position==0){
            holder.castAndCrewRecyclerView.adapter = CastAdapter(creditItem.cast,context)
        }
        else{
            holder.castAndCrewRecyclerView.adapter = CrewAdapter(creditItem.crew,context)

        }
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        internal var castAndCrewRecyclerView : RecyclerView
        init {
            castAndCrewRecyclerView = itemView.findViewById(R.id.cast_and_crew_rv)
        }
    }
    private fun fixRecyclerView(recyclerView: RecyclerView){
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        layoutManager.scrollToPosition(0)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
    }
}
