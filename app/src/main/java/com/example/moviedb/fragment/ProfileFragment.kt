package com.example.moviedb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.moviedb.R
import com.google.android.material.tabs.TabLayout

class ProfileFragment : Fragment(){
    lateinit var tabLayout: TabLayout
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile,container,false)
        tabLayout = activity?.findViewById(R.id.tabLayout)!!
        return view
    }
    override fun onStart() {
        super.onStart()
        tabLayout?.getTabAt(2)?.icon = ContextCompat.getDrawable(context!!,R.drawable.ic_tab_profile_selected)
    }

    override fun onStop() {
        super.onStop()
        tabLayout?.getTabAt(2)?.icon = ContextCompat.getDrawable(context!!,R.drawable.ic_tab_profile)

    }
}