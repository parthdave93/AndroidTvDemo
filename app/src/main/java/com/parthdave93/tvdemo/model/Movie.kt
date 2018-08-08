package com.parthdave93.tvdemo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Parth Dave on 07-08-2018.
 */
class Movie : Serializable{
    @SerializedName("title")
    var title: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("picture")
    var imageUrl: String? = null
    @SerializedName("producer")
    var producer: String? = null
    @SerializedName("similar_movies")
    var similarMovie: ArrayList<SimilarMovie>? = null
}