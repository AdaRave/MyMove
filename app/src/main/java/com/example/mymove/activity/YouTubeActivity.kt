package com.example.mymove.activity


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymove.R
import com.example.mymove.adapter.FilmAdapter
import com.example.mymove.model.getmovie.GetMovieForYoutube
import com.example.mymove.model.rotation.MovieRated
import com.example.mymove.model.rotation.Results
import com.example.mymove.service.Instance
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.fragment_container.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YouTubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    val KEY =  "AIzaSyAPJtCoKEQlB6xtLToO4V-XbQnBr8-22dg"
    var VIDEO_ID = ""
    var MOVIE_ID = 12


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_tube)

        val bundle = intent.extras
        MOVIE_ID = bundle!!.get("id").toString().toInt()

        getMovieForYoutube()


    }

    private fun getMovieForYoutube() {
        val movieService = Instance.apiService
        val call: Call<GetMovieForYoutube> = movieService.getMovieForYouTube(MOVIE_ID, getString(R.string.KEY))
        call.enqueue(object : Callback<GetMovieForYoutube>{
            override fun onFailure(call: Call<GetMovieForYoutube>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<GetMovieForYoutube>, response: Response<GetMovieForYoutube>) {
                val movie = response.body()
                if (movie != null) {
                    var resultList = movie.results
                    for (results in resultList!!){
                            VIDEO_ID = results.key!!.toString()
                    }
                }

            }

        })
        var youTubePlayerView : YouTubePlayerView = findViewById(R.id.youtubeView)
        youTubePlayerView.initialize(KEY, this)

    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        youTubePlayer: YouTubePlayer?,
        p2: Boolean
    ) {
        Toast.makeText(this, VIDEO_ID, Toast.LENGTH_SHORT).show()
        youTubePlayer?.loadVideo(VIDEO_ID)
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Toast.makeText(this, "Oh " + p1, Toast.LENGTH_LONG)
    }
}