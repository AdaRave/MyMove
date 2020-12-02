package com.example.mymove.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.mymove.R
import kotlinx.android.synthetic.main.activity_movie_information.*

class MovieInformation : AppCompatActivity() {

    var id = 12;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_information)
        val bundle = intent.extras

        if (bundle!=null){
            id = bundle.get("id").toString().toInt()
            TitleTextView.text = bundle.get("title").toString()
            YearTextView.text = bundle.get("year").toString()
            overviewTextView.text = bundle.get("overview").toString()
            val imagePath = "https://image.tmdb.org/t/p/w500" + bundle.get("image").toString()
            Glide.with(this).load(imagePath)
                .placeholder(R.drawable.ic_baseline_remove_from_queue_24)
                .into(poster)

        }

    }

    fun youtube(view: View) {
        val intent = Intent(this, YouTubeActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}