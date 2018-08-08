package com.parthdave93.tvdemo.networkresponse

import com.google.gson.annotations.SerializedName
import com.parthdave93.tvdemo.model.Movie

/**
 * Created by Parth Dave on 07-08-2018.
 */
class MovieHeader{
    @SerializedName("title")
    var title : String? = null

    @SerializedName("cards")
    var cards : ArrayList<Movie>? = null
}