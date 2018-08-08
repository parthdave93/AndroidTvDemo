package com.parthdave93.tvdemo.di.networkrepository

import android.arch.lifecycle.LiveData
import com.parthdave93.tvdemo.networkresponse.CardHeaderResponse
import com.parthdave93.tvdemo.networkresponse.CommonResponse
import com.parthdave93.tvdemo.networkresponse.MovieResponse

/**
 * Created by Parth Dave on 07-08-2018.
 */
interface NetworkRepository{
    fun login(username: String,password: String) : LiveData<CommonResponse>
    fun fetchCardsWithHeader() : LiveData<CardHeaderResponse>
    fun fetchMovies() : LiveData<MovieResponse>
}