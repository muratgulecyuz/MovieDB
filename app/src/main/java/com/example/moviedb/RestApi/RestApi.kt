package com.example.pet.RestApi

import com.example.moviedb.model.CastCrewPojo.CreditsPojo
import com.example.moviedb.model.MovieDetailPojo.MovieDetailPojo
import com.example.moviedb.model.MoviesPojo.MovieNowPlayingPojo
import com.example.moviedb.model.MoviesPojo.MoviePopularPojo
import com.example.moviedb.model.MoviesPojo.MovieTopRatedMoviesPojo
import com.example.moviedb.model.TvDetailPojo.TvDetailPojo
import com.example.moviedb.model.TvPojo.TvPojo
import com.example.moviedb.model.TvPojo.TvPopularPojo
import com.example.moviedb.model.VideosPojo.VideosPojo

import retrofit2.Call
import retrofit2.http.*

interface RestApi {


    @GET("movie/top_rated?api_key=955c743dcb15cb77258ad77f462495e1&language=en-US")
    fun getTopRatedMoviesRepo(@Query("page")page:Int):Call<MovieTopRatedMoviesPojo>

    @GET("movie/now_playing?api_key=955c743dcb15cb77258ad77f462495e1&language=en-US")
    fun getNowPlayingRepo(@Query("page")page:Int):Call<MovieNowPlayingPojo>

    @GET("movie/popular?api_key=955c743dcb15cb77258ad77f462495e1&language=en-US")
    fun getPopularRepo(@Query("page")page:Int):Call<MoviePopularPojo>

    @GET("tv/top_rated?api_key=955c743dcb15cb77258ad77f462495e1&language=en-US")
    fun getTvRepo(@Query("page")page:Int):Call<TvPojo>

    @GET("tv/popular?api_key=955c743dcb15cb77258ad77f462495e1&language=en-US")
    fun getTvPopularRepo(@Query("page")page:Int):Call<TvPopularPojo>

    @GET("movie/{movie_id}?api_key=955c743dcb15cb77258ad77f462495e1&language=en-US")
    fun getMovieDetailsRepo(@Path("movie_id")movieId:Int):Call<MovieDetailPojo>

    @GET("tv/{tv_id}?api_key=955c743dcb15cb77258ad77f462495e1&language=en-US")
    fun getTvDetailsRepo(@Path("tv_id")tvId:Int):Call<TvDetailPojo>

    @GET("{category}/{movie_id}/credits?api_key=955c743dcb15cb77258ad77f462495e1&language=en-US")
    fun getCreditsRepo(@Path("category")category:String,@Path("movie_id")movieId:Int):Call<CreditsPojo>

    @GET("{category}/{movie_id}/videos?api_key=955c743dcb15cb77258ad77f462495e1&language=en-US")
    fun getTrailerRepo(@Path("category")category:String,@Path("movie_id")movieId:Int):Call<VideosPojo>




}
