package com.example.mymove.service

import com.example.mymove.model.getmovie.GetMovieForYoutube
import com.example.mymove.model.popular.MovieNow
import com.example.mymove.model.rotation.MovieRated
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("3/movie/top_rated")
    fun getRated(@Query("api_key") api_key : String) : Call<MovieRated>

    @GET("3/movie/now_playing")
    fun getNowMovie(@Query("api_key") api_key : String) : Call<MovieNow>

    @GET("3/movie/{movie_id}/videos")
    fun getMovieForYouTube(@Path ("movie_id")  movie_id : Int,
                           @Query("api_key") api_key : String) : Call<GetMovieForYoutube>

    @GET("3/search/movie")
    fun getSearchMovie(@Query("api_key") api_key : String, @Query("query") query : String) : Call<MovieRated>

}