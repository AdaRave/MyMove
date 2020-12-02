package com.example.mymove.model.rotation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieRated {
    @SerializedName("page")
    @Expose
    var page : Int? = null

    @SerializedName( "results")
    @Expose
    var results : List<Results>? = null

    @SerializedName("total_results")
    @Expose
    var total_results : Int? = null

    @SerializedName("total_pages")
    @Expose
    var total_pages : Int? = null

}