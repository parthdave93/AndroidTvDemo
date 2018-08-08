package com.parthdave93.tvdemo.di.networkpresenters.movie

import android.arch.lifecycle.LiveData
import com.parthdave93.tvdemo.di.networkrepository.NetworkRepository
import com.parthdave93.tvdemo.networkresponse.CardHeaderResponse
import com.parthdave93.tvdemo.networkresponse.MovieResponse
import javax.inject.Inject

/**
 * Created by Parth Dave on 07-08-2018.
 */
class MoviePresenterImpl @Inject constructor(val networkRepository: NetworkRepository)  : MoviePresenterContract.MoviePresenter {
    override fun fetchLists() : LiveData<MovieResponse> {
        return networkRepository.fetchMovies()
    }

}