package com.example.moviedb.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.Manager
import com.example.moviedb.R
import com.example.moviedb.RecyclerViewFeature
import com.example.moviedb.Rest
import com.example.moviedb.RestApi.ApiClient
import com.example.moviedb.activity.DetailActivity
import com.example.moviedb.adapter.TvAdapter
import com.example.moviedb.adapter.TvPopularAdapter
import com.example.moviedb.model.TvPojo.TvPojo
import com.example.moviedb.model.TvPojo.TvResult
import com.example.moviedb.model.TvPojo.TvPopularPojo
import com.example.pet.RestApi.RestApi
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvFragment : Fragment(),TvAdapter.OnTvPositionListener,TvPopularAdapter.OnTvPopularPositionListener{
    lateinit var tvRecyclerView: RecyclerView
    lateinit var tvPopularRecyclerView: RecyclerView
    lateinit var tvList: MutableList<TvResult>
    lateinit var tvPopularList: MutableList<TvResult>
    lateinit var tvLayoutManager: LinearLayoutManager
    lateinit var tvPopularLayoutManager: LinearLayoutManager
    lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tv,container,false)
        init(view)
        getTv()
        getTvPopular()
        return view
    }
    private fun init(view: View){
        tvRecyclerView = view.findViewById(R.id.tv_rv)
        tvPopularRecyclerView = view.findViewById(R.id.tv_popular_rv)
        tvList = mutableListOf()
        tvPopularList = mutableListOf()
        tvLayoutManager = Manager().getHorizontalManager(context!!)
        tvPopularLayoutManager = Manager().getVerticalManager(context!!)
        tabLayout = activity?.findViewById(R.id.tabLayout)!!

    }
    private fun getTv(){
        Rest().getRest().getTvRepo(1).enqueue(object : Callback<TvPojo>{
            override fun onFailure(call: Call<TvPojo>, t: Throwable) {
                Log.i("TAG","Tv Error: "+t.message)

            }

            override fun onResponse(call: Call<TvPojo>, response: Response<TvPojo>) {
                    tvList = response.body()!!.results
                    tvRecyclerView.adapter = TvAdapter(tvList,this@TvFragment,context!!)
                    RecyclerViewFeature().fixRecyclerView(tvRecyclerView,tvLayoutManager)

            }

        })
    }
    private fun getTvPopular(){
        Rest().getRest().getTvPopularRepo(1).enqueue(object : Callback<TvPopularPojo>{
            override fun onFailure(call: Call<TvPopularPojo>, t: Throwable) {
                Log.i("TAG","Tv Popular Error: "+t.message)

            }

            override fun onResponse(call: Call<TvPopularPojo>, response: Response<TvPopularPojo>) {

                    tvPopularList = response.body()!!.results
                    tvPopularList.sortByDescending { it.voteAverage }
                    tvPopularRecyclerView.adapter = TvPopularAdapter(tvPopularList,this@TvFragment,context!!)
                    RecyclerViewFeature().fixRecyclerView(tvPopularRecyclerView,tvPopularLayoutManager)


            }

        })
    }

    override fun onStart() {
        super.onStart()
        tabLayout?.getTabAt(1)?.icon = ContextCompat.getDrawable(context!!,R.drawable.ic_tab_tv_selected)
    }

    override fun onStop() {
        super.onStop()
        tabLayout?.getTabAt(1)?.icon = ContextCompat.getDrawable(context!!,R.drawable.ic_tab_tv)

    }

    override fun onTvClick(position: Int) {
        goDetails(tvList,position)
    }

    override fun onTvPopularClick(position: Int) {
        goDetails(tvPopularList,position)
    }

    private fun goDetails(list:MutableList<TvResult>, position: Int){
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("id",list[position].id)
        intent.putExtra("category","tv")
        startActivity(intent)
    }


}