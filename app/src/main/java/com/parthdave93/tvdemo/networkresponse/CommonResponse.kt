package com.parthdave93.tvdemo.networkresponse

import com.google.gson.annotations.SerializedName

/**
 * Created by Parth Dave on 02-07-2018.
 */

open class CommonResponse{
    @SerializedName("status")
    var status: Int = 0
    @SerializedName("message")
    var message : String? = null
    @SerializedName("error")
    var error : String? = null

    constructor() {
    }

    constructor(status: Int, message: String?) {
        this.status = status
        this.message = message
    }
}