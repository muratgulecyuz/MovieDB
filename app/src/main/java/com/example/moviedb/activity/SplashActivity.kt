package com.example.moviedb.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.moviedb.R
import com.example.moviedb.StatusBar


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        StatusBar(window).setTranslucentStatusBar()
        StatusBar(window).setStatusBarColor(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
        Handler().postDelayed(this::goMain, 3000)

    }
    private fun goMain(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()

    }
}
