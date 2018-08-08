package com.parthdave93.tvdemo.di.networkpresenters.cardwithheader

import android.arch.lifecycle.LiveData
import com.parthdave93.tvdemo.networkresponse.CardHeader
import com.parthdave93.tvdemo.networkresponse.CardHeaderResponse

/**
 * Created by Parth Dave on 07-08-2018.
 */
interface CardWithHeaderPresenterContract{

    interface CardWithHeaderPresenter{
        fun fetchLists() : LiveData<CardHeaderResponse>
    }

    interface CardWithHeaderView{
        fun showLoading()
        fun showData(items : List<CardHeader>)
        fun showError()
    }

}