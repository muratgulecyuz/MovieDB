package com.example.moviedb

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewFeature {
    fun fixRecyclerView(recyclerView: RecyclerView, layoutManager: LinearLayoutManager){
        layoutManager.scrollToPosition(0)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
    }
}