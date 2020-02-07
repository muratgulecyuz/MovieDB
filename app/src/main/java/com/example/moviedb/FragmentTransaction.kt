package com.example.moviedb

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentTransaction {
    fun replaceFragment(supportFragmentManager:FragmentManager,fragment: Fragment,container:Int){
        supportFragmentManager.beginTransaction().replace(container,fragment).commit()
    }
}