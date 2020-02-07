package com.example.moviedb

import android.view.Window
import android.view.WindowManager

class StatusBar(internal var window:Window) {
    fun setTranslucentStatusBar(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }
    fun setStatusBarColor(color:Int){
        window.decorView.systemUiVisibility = color
    }
}