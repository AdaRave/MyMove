package com.example.mymove.model.popular

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Dates {
    @SerializedName("total_pages")
    @Expose
    var total_pages: Int? = null

    @SerializedName("total_results")
    @Expose
    var total_results: Int? = null


}