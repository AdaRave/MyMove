package com.example.mymove.model.popular

import com.example.mymove.model.rotation.Results
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieNow {
    @SerializedName("page")
    @Expose
    var page : Int? = null

    @SerializedName("results")
    @Expose
    var results : List<Results>? = null

    @SerializedName("dates")
    @Expose
    var dates : Dates? = null

    @SerializedName("total_pages")
    @Expose
    var total_pages : Int? = null

    @SerializedName("total_results")
    @Expose
    var total_results : Int? = null


}