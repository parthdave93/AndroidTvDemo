package com.parthdave93.tvdemo.di.networkpresenters.movie

import android.arch.lifecycle.LiveData
import com.parthdave93.tvdemo.model.Movie
import com.parthdave93.tvdemo.networkresponse.MovieResponse

/**
 * Created by Parth Dave on 07-08-2018.
 */
interface MoviePresenterContract{

    interface MoviePresenter{
        fun fetchLists() : LiveData<MovieResponse>
    }

    interface MovieView{
        fun showLoading()
        fun showData(items : List<Movie>)
        fun showError()
    }

}