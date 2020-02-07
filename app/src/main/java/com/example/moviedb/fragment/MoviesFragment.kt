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
import com.example.moviedb.adapter.NowPlayingAdapter
import com.example.moviedb.adapter.PopularAdapter
import com.example.moviedb.adapter.TopRatedMoviesAdapter
import com.example.moviedb.model.MoviesPojo.MovieNowPlayingPojo
import com.example.moviedb.model.MoviesPojo.MoviePopularPojo
import com.example.moviedb.model.MoviesPojo.MovieResult
import com.example.moviedb.model.MoviesPojo.MovieTopRatedMoviesPojo
import com.example.pet.RestApi.RestApi
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesFragment : Fragment(),TopRatedMoviesAdapter.OnTopRatedPositionListener,NowPlayingAdapter.OnNowPlayingPositionListener,
PopularAdapter.OnPopularPositionListener{
    lateinit var topRatedList:MutableList<MovieResult>
    lateinit var nowPlayingList:MutableList<MovieResult>
    lateinit var popularList:MutableList<MovieResult>
    lateinit var topRatedMoviesRecyclerViewLayoutManager: LinearLayoutManager
    lateinit var nowPlayingRecyclerViewLayoutManager: LinearLayoutManager
    lateinit var popularRecyclerViewLayoutManager: LinearLayoutManager
    lateinit var topRatedMoviesRecyclerView:RecyclerView
    lateinit var nowPlayingRecyclerView: RecyclerView
    lateinit var popularRecyclerView: RecyclerView
    lateinit var tabLayout:TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movies,container,false)
        init(view)
        getTopRatedMovies()
        getNowPlayingMovies()
        getPopularMovies()
        return view

    }
    private fun init(view:View){
        topRatedList = mutableListOf()
        nowPlayingList = mutableListOf()
        popularList = mutableListOf()
        topRatedMoviesRecyclerView = view.findViewById(R.id.top_rated_movies_rv)
        nowPlayingRecyclerView = view.findViewById(R.id.now_playing_rv)
        popularRecyclerView = view.findViewById(R.id.popular_rv)
        topRatedMoviesRecyclerViewLayoutManager = Manager().getHorizontalManager(context!!)
        nowPlayingRecyclerViewLayoutManager = Manager().getHorizontalManager(context!!)
        popularRecyclerViewLayoutManager = Manager().getHorizontalManager(context!!)
        tabLayout = activity?.findViewById(R.id.tabLayout)!!

    }

    private fun getTopRatedMovies(){
        val call = Rest().getRest().getTopRatedMoviesRepo(1)
        call.enqueue(object : Callback<MovieTopRatedMoviesPojo>{
            override fun onFailure(call: Call<MovieTopRatedMoviesPojo>, t: Throwable) {
                Log.i("TAG","Top Rated Movie Error: "+t.message)
            }

            override fun onResponse(call: Call<MovieTopRatedMoviesPojo>, response: Response<MovieTopRatedMoviesPojo>) {

                    topRatedList = response.body()!!.results
                    RecyclerViewFeature().fixRecyclerView(topRatedMoviesRecyclerView,topRatedMoviesRecyclerViewLayoutManager)
                    topRatedMoviesRecyclerView.adapter = TopRatedMoviesAdapter(topRatedList,this@MoviesFragment,context!!)
            }

        })
    }
    private fun getNowPlayingMovies(){
        Rest().getRest().getNowPlayingRepo(1).enqueue(object : Callback<MovieNowPlayingPojo>{
            override fun onFailure(call: Call<MovieNowPlayingPojo>, t: Throwable) {
                Log.i("TAG","Now Playing Movie Error: "+t.message)

            }

            override fun onResponse(call: Call<MovieNowPlayingPojo>, response: Response<MovieNowPlayingPojo>) {
                    nowPlayingList = response.body()!!.results
                    RecyclerViewFeature().fixRecyclerView(nowPlayingRecyclerView,nowPlayingRecyclerViewLayoutManager)
                    nowPlayingRecyclerView.adapter = NowPlayingAdapter(nowPlayingList,this@MoviesFragment,context!!)
            }

        })
    }
    private fun getPopularMovies(){
        Rest().getRest().getPopularRepo(1).enqueue(object : Callback<MoviePopularPojo>{
            override fun onFailure(call: Call<MoviePopularPojo>, t: Throwable) {
                Log.i("TAG","Popular Movie Error: "+t.message)

            }

            override fun onResponse(call: Call<MoviePopularPojo>, response: Response<MoviePopularPojo>) {

                    popularList = response.body()!!.results
                    popularList.sortByDescending { it.voteAverage }
                    RecyclerViewFeature().fixRecyclerView(popularRecyclerView,popularRecyclerViewLayoutManager)
                    popularRecyclerView.adapter = PopularAdapter(popularList,this@MoviesFragment,context!!)

            }

        })
    }


    override fun onStart() {
        super.onStart()
        tabLayout?.getTabAt(0)?.icon = ContextCompat.getDrawable(context!!,R.drawable.ic_tab_movies_selected)
    }

    override fun onStop() {
        super.onStop()
        tabLayout?.getTabAt(0)?.icon = ContextCompat.getDrawable(context!!,R.drawable.ic_tab_movies)

    }

    override fun onTopRatedClick(position: Int) {
        goDetails(topRatedList,position)

    }

    override fun onNowPlayingClick(position: Int) {
        goDetails(nowPlayingList,position)

    }

    override fun onPopularClick(position: Int) {
        goDetails(popularList,position)
    }
    private fun goDetails(list:MutableList<MovieResult>, position: Int){
        val intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra("id",list[position].id)
        intent.putExtra("category","movie")
        startActivity(intent)
    }



}