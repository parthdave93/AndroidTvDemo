package com.parthdave93.tvdemo.di.networkrepository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.parthdave93.tvdemo.networkresponse.CardHeaderResponse
import com.parthdave93.tvdemo.networkresponse.CommonResponse
import com.parthdave93.tvdemo.networkresponse.MovieResponse
import kotlinx.coroutines.experimental.launch
import com.parthdave93.tvdemo.di.WebApi
import javax.inject.Inject

/**
 * Created by Parth Dave on 07-08-2018.
 */
class NetworkRepositoryImpl @Inject constructor(val api: WebApi) : NetworkRepository {

    override fun login(username: String, password: String): LiveData<CommonResponse> {
        var response = MutableLiveData<CommonResponse>()
        launch {
            var login = api.login()
            var result = login.await()
            if (result.isSuccessful) {
                response.postValue(result.body())
            } else {
                response.postValue(CommonResponse(result.code(), result.message()))
            }
        }
        return response
    }

    override fun fetchCardsWithHeader(): LiveData<CardHeaderResponse> {
        var response = MutableLiveData<CardHeaderResponse>()
        launch {
            var callApi = api.fetchCardsWithHeader()
            var result = callApi.await()
            if (result.isSuccessful) {
                response.postValue(CardHeaderResponse(result.code(), result.message(), result.body()))
            } else {
                response.postValue(CardHeaderResponse(result.code(), result.message()))
            }
        }
        return response
    }


    override fun fetchMovies(): LiveData<MovieResponse> {
        var response = MutableLiveData<MovieResponse>()
        launch {
            var callApi = api.fetchMovies()
            var result = callApi.await()
            if (result.isSuccessful) {
                response.postValue(MovieResponse(result.code(), result.message(), result.body()))
            } else {
                response.postValue(MovieResponse(result.code(), result.message()))
            }
        }
        return response
    }
}