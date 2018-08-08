package com.parthdave93.tvdemo.di.networkpresenters.cardwithheader

import android.arch.lifecycle.LiveData
import com.parthdave93.tvdemo.di.networkrepository.NetworkRepository
import com.parthdave93.tvdemo.networkresponse.CardHeaderResponse
import javax.inject.Inject

/**
 * Created by Parth Dave on 07-08-2018.
 */
class CardWithHeaderPresenterImpl @Inject constructor(val networkRepository: NetworkRepository)  : CardWithHeaderPresenterContract.CardWithHeaderPresenter {
    override fun fetchLists() : LiveData<CardHeaderResponse> {
        return networkRepository.fetchCardsWithHeader()
    }

}