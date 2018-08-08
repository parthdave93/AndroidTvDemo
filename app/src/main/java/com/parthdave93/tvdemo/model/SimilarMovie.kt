package com.parthdave93.tvdemo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Parth Dave on 07-08-2018.
 */
class SimilarMovie: Serializable {
    @SerializedName("title")
    var title: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("picture")
    var imageUrl: String? = null
    @SerializedName("producer")
    var producer: String? = null
}