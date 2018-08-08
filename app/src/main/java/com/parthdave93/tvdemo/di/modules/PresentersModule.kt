package com.parthdave93.tvdemo.di

import com.parthdave93.tvdemo.di.networkpresenters.cardwithheader.CardWithHeaderPresenterContract
import com.parthdave93.tvdemo.di.networkpresenters.cardwithheader.CardWithHeaderPresenterImpl
import com.parthdave93.tvdemo.di.networkpresenters.movie.MoviePresenterContract
import com.parthdave93.tvdemo.di.networkpresenters.movie.MoviePresenterImpl
import com.parthdave93.tvdemo.di.networkrepository.NetworkRepository
import com.parthdave93.tvdemo.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by Parth Dave on 07-08-2018.
 */
@Module
class PresentersModule{

    @Provides
    @ActivityScope
    fun providesCardWithHeaderPresenter(networkRepository: NetworkRepository): CardWithHeaderPresenterContract.CardWithHeaderPresenter{
        return CardWithHeaderPresenterImpl(networkRepository)
    }

    @Provides
    @ActivityScope
    fun providesMoviePresenter(networkRepository: NetworkRepository): MoviePresenterContract.MoviePresenter{
        return MoviePresenterImpl(networkRepository)
    }


}