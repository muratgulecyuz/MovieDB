package com.example.moviedb.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.*
import com.example.moviedb.adapter.CreditsAdapter
import com.example.moviedb.model.CastCrewPojo.CreditsPojo
import com.example.moviedb.model.MovieDetailPojo.MovieDetailPojo
import com.example.moviedb.model.TvDetailPojo.TvDetailPojo
import com.example.moviedb.model.VideosPojo.VideosPojo
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : YouTubeBaseActivity(),YouTubePlayer.OnInitializedListener {
    lateinit var bundle: Bundle
    lateinit var category:String
    lateinit var layoutManager: LinearLayoutManager
    var id = 0
    lateinit var videoLinkList: MutableList<String>
    lateinit var starsList:Array<ImageView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        StatusBar(window).setTranslucentStatusBar()
        StatusBar(window).setStatusBarColor(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
        define()
        getFilters()
        getDetails()
        getCredits()
        getTrailer()

    }
    private fun define(){
        bundle = intent.extras!!
        layoutManager = Manager().getHorizontalManager(applicationContext)
        videoLinkList = mutableListOf()
        starsList = arrayOf(star1,star2,star3,star4,star5)

    }
    private fun getFilters(){
        id = bundle.getInt("id")
        category = bundle.getString("category").toString()
    }
    private fun getDetails(){
        Log.i("asdasd",id.toString())
        var genres: StringBuilder = StringBuilder()
        if (category.equals("movie")) {
            Rest().getRest().getMovieDetailsRepo(id).enqueue(object : Callback<MovieDetailPojo> {
                override fun onFailure(call: Call<MovieDetailPojo>, t: Throwable) {
                    Log.i("TAG","Movie Detail Error: "+t.message)

                }

                override fun onResponse(call: Call<MovieDetailPojo>, response: Response<MovieDetailPojo>) {
                    movie_tv_name_tv.text = response.body()?.title
                    for (i in 0..response.body()?.genres!!.size - 1) {
                        genres.append(response.body()!!.genres[i].name)
                        if (i != response.body()?.genres!!.size - 1) genres.append(",")
                    }
                    genre_tv.text = genres
                    Picasso.get().load("https://image.tmdb.org/t/p/w500/" + response.body()!!.posterPath).into(movie_tv_iv)
                    rate_tv.text = response.body()?.voteAverage.toString()
                    overview_tv.text = response.body()?.overview
                    setStar(response.body()!!.voteAverage)


                }

            })
        }
        else if (category.equals("tv")){
            Rest().getRest().getTvDetailsRepo(id).enqueue(object : Callback<TvDetailPojo>{
                override fun onFailure(call: Call<TvDetailPojo>, t: Throwable) {
                    Log.i("TAG","Tv Detail Error: "+t.message)

                }

                override fun onResponse(call: Call<TvDetailPojo>, response: Response<TvDetailPojo>) {
                    movie_tv_name_tv.text = response.body()?.name
                    for (i in 0..response.body()?.genres!!.size - 1) {
                        genres.append(response.body()!!.genres[i].name)
                        if (i != response.body()?.genres!!.size - 1) genres.append(",")
                    }
                    genre_tv.text = genres
                    Picasso.get().load("https://image.tmdb.org/t/p/w500/" + response.body()!!.posterPath).into(movie_tv_iv)
                    rate_tv.text = response.body()?.voteAverage.toString()
                    overview_tv.text = response.body()?.overview
                    setStar(response.body()!!.voteAverage)





                }

            })
        }
    }
    private fun getCredits(){
        Rest().getRest().getCreditsRepo(category,id).enqueue(object : Callback<CreditsPojo>{
            override fun onFailure(call: Call<CreditsPojo>, t: Throwable) {
                Log.i("TAG","Credit Error: "+t.message)

            }

            override fun onResponse(call: Call<CreditsPojo>, response: Response<CreditsPojo>) {
                RecyclerViewFeature().fixRecyclerView(creditsRecyclerView,layoutManager)
                creditsRecyclerView.adapter = CreditsAdapter(response.body()!!,applicationContext)
            }

        })
    }

    private fun getTrailer(){

        getVideoLinks()
        play_iv.setOnClickListener {
            trailer.initialize(YoutubeConfig.getApiKey(),this)
            play_iv.visibility = View.INVISIBLE
            share_iv.visibility = View.INVISIBLE
        }


    }

    override fun onInitializationSuccess(provide: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, b: Boolean) {
        youtubePlayer?.loadVideos(videoLinkList)

    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
    }
    private fun setStar(average:Double){
        for (i in 0..(average/2).toInt()){
            starsList[i].setImageResource(R.drawable.ic_starselected)
        }
    }
    private fun shareLink(url:String){

            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Sharing Url")
            intent.putExtra(Intent.EXTRA_TEXT, url)
            startActivity(Intent.createChooser(intent, "Share Url"))

    }
    private fun getVideoLinks(){
        Rest().getRest().getTrailerRepo(category,id).enqueue(object : Callback<VideosPojo>{
            override fun onFailure(call: Call<VideosPojo>, t: Throwable) {
                Log.i("TAG","Video Link Error: "+t.message)

            }

            override fun onResponse(call: Call<VideosPojo>, response: Response<VideosPojo>) {
                for (i in 0..response.body()?.results!!.size-1){
                    videoLinkList.add(response.body()!!.results[i].key)
                }
                share_iv.setOnClickListener {
                    shareLink("https://www.youtube.com/watch?v="+response.body()!!.results[0].key)
                }
            }

        })
    }
}
