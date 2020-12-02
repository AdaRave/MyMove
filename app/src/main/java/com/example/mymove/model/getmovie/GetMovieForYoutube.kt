package com.example.mymove.model.getmovie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetMovieForYoutube {
    @SerializedName("id")
    @Expose
    var id : Int ? = null

    @SerializedName("results")
    @Expose
    var results : List<Results>? = null

}