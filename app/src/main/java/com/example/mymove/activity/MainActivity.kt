package com.example.mymove.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Filter
import android.widget.Filterable
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymove.R
import com.example.mymove.adapter.FilmAdapter
import com.example.mymove.fragment.ContainerFragment

import com.example.mymove.model.popular.MovieNow
import com.example.mymove.model.rotation.MovieRated
import com.example.mymove.model.rotation.Results
import com.example.mymove.service.Instance
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_button.*
import kotlinx.android.synthetic.main.fragment_container.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), FilmAdapter.OnItemClickedListener {
    var result : ArrayList<Results>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragment : Fragment = ContainerFragment()
        var fm : FragmentManager? = supportFragmentManager
        var ft : FragmentTransaction = fm?.beginTransaction()!!
        ft.replace(R.id.frame, fragment)
        ft.commit()

        searchMovie()

    }

    private fun searchMovie() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    val movieService = Instance.apiService
                    val call: Call<MovieRated> = movieService.getSearchMovie(getString(R.string.KEY), p0)
                    call.enqueue(object : Callback<MovieRated>{
                        override fun onFailure(call: Call<MovieRated>, t: Throwable) {
                        }

                        override fun onResponse(call: Call<MovieRated>, response: Response<MovieRated>) {
                            val movie = response.body()
                            if (movie!=null){
                                result = movie.results as ArrayList<Results>?
                                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                                val adapter = FilmAdapter(result!!, this@MainActivity, this@MainActivity)
                                recyclerView.adapter = adapter
                            }
                        }

                    })

                }
                return true
            }

        })
    }


    fun clickGetRated(view: View){
        buttTopTMDb.setBackgroundResource(R.drawable.ic_baseline_local_movies_24)
        buttPopular.setBackgroundResource(R.drawable.ic_baseline_movie_filter_24_ok)
        butNowPlaying.setBackgroundResource(R.drawable.ic_baseline_movie_24_ok)

        val movieService = Instance.apiService
        val call: Call<MovieRated> = movieService.getRated(getString(R.string.KEY))
        call.enqueue(object : Callback<MovieRated>{
            override fun onFailure(call: Call<MovieRated>, t: Throwable) {
            }

            override fun onResponse(call: Call<MovieRated>, response: Response<MovieRated>) {
                val movie = response.body()
                if (movie!=null){
                    result = movie.results as ArrayList<Results>?
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    val adapter = FilmAdapter(result!!, this@MainActivity, this@MainActivity)
                    recyclerView.adapter = adapter
                }
            }

        })
    }

    fun clickNowMovie(view: View) {
        buttTopTMDb.setBackgroundResource(R.drawable.ic_baseline_local_movies_24_ok)
        buttPopular.setBackgroundResource(R.drawable.ic_baseline_movie_filter_24_ok)
        butNowPlaying.setBackgroundResource(R.drawable.ic_baseline_movie_24)

        val movieService = Instance.apiService

        val call: Call<MovieNow> = movieService.getNowMovie(getString(R.string.KEY))
        call.enqueue(object : Callback<MovieNow> {
            override fun onFailure(call: Call<MovieNow>, t: Throwable) {
                Log.d("ERRORS", t.toString())
            }

            override fun onResponse(call: Call<MovieNow>, response: Response<MovieNow>) {
                val movie = response.body()
                if (movie != null) {
                    result = movie.results as ArrayList<Results>?
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    val adapter = FilmAdapter(result!!, this@MainActivity, this@MainActivity)
                    recyclerView.adapter = adapter
                }
            }

        })
    }

    override fun onItemClicked(item: Results, position: Int) {
        intent = Intent(this, MovieInformation::class.java)
        intent.putExtra("title", item.title)
        intent.putExtra("year", item.release_date)
        intent.putExtra("image", item.poster_path)
        intent.putExtra("id", item.id)
        intent.putExtra("overview", item.overview)
        startActivity(intent)
    }



}


