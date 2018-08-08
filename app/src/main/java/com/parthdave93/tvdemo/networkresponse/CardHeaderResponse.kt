package com.parthdave93.tvdemo.networkresponse


/**
 * Created by Parth Dave on 07-08-2018.
 */
class CardHeaderResponse(status: Int, message: String?) : CommonResponse(status,message) {
    var data: List<CardHeader>? = null

    constructor(status: Int, message: String?, data: List<CardHeader>?) : this(status,message) {
        this.status = status
        this.message = message
        this.data = data
    }
}