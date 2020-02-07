package com.example.moviedb

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class Manager {
    fun getHorizontalManager(context: Context): LinearLayoutManager{
        return LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }
    fun getVerticalManager(context: Context): LinearLayoutManager{
        return LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    }
}