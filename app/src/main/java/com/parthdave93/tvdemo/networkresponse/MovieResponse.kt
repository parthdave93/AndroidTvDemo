package com.parthdave93.tvdemo.networkresponse


/**
 * Created by Parth Dave on 07-08-2018.
 */
class MovieResponse(status: Int, message: String?) : CommonResponse(status, message) {
    var data: List<MovieHeader>? = null

    constructor(status: Int, message: String?, data: List<MovieHeader>?) : this(status, message) {
        this.status = status
        this.message = message
        this.data = data
    }
}