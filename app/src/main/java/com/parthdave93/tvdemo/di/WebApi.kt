package com.parthdave93.tvdemo.di


import com.parthdave93.tvdemo.networkresponse.CardHeader
import com.parthdave93.tvdemo.networkresponse.CommonResponse
import com.parthdave93.tvdemo.networkresponse.MovieHeader
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.POST

/**
 * Created by Parth Dave on 29-06-2018.
 */

interface WebApi {

    companion object {
        const val API_SUCCESS = 200
        const val API_FAIL = 400

        const val BASE_URL = "http://www.google.com/"
        const val LOGIN_URL = "login"
        const val SEND_OTP = "send_otp"
        const val HEADER_WITH_CARD_LIST = "header_with_card_list"
        const val LOGOUT_URL = "logout"
    }
    @POST(LOGIN_URL)
    fun login() : Deferred<Response<CommonResponse>>

    @POST(HEADER_WITH_CARD_LIST)
    fun fetchCardsWithHeader() : Deferred<Response<List<CardHeader>>>

    @POST(HEADER_WITH_CARD_LIST)
    fun fetchMovies() : Deferred<Response<List<MovieHeader>>>

    @POST(LOGOUT_URL)
    fun logout() : Deferred<Response<CommonResponse>>

}