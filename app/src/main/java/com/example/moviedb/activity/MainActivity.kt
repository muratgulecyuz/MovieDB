package com.example.moviedb.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.moviedb.FragmentTransaction
import com.example.moviedb.R
import com.example.moviedb.StatusBar
import com.example.moviedb.fragment.MoviesFragment
import com.example.moviedb.fragment.ProfileFragment
import com.example.moviedb.fragment.TvFragment

import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var moviesFragment: MoviesFragment
    lateinit var tvFragment: TvFragment
    lateinit var profileFragment: ProfileFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBar(window).setTranslucentStatusBar()
        StatusBar(window).setStatusBarColor(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        init()
        initFragment()
        replaceFragmentRelatedTab()

    }
    private fun init(){
        moviesFragment = MoviesFragment()
        tvFragment = TvFragment()
        profileFragment = ProfileFragment()
    }
    private fun replaceFragmentRelatedTab(){
        tabLayout.addOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->FragmentTransaction().replaceFragment(supportFragmentManager,moviesFragment,R.id.frameLayout)
                    1->FragmentTransaction().replaceFragment(supportFragmentManager,tvFragment,R.id.frameLayout)
                    2->FragmentTransaction().replaceFragment(supportFragmentManager,profileFragment,R.id.frameLayout)

                }            }

        })
    }
    private fun initFragment(){
        FragmentTransaction().replaceFragment(supportFragmentManager,moviesFragment,R.id.frameLayout)

    }

}
