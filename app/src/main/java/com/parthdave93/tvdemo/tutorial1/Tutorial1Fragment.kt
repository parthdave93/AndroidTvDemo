package com.parthdave93.tvdemo.tutorial1

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import android.support.v17.leanback.app.BrowseSupportFragment
import android.support.v17.leanback.widget.ArrayObjectAdapter
import android.support.v17.leanback.widget.HeaderItem
import android.support.v17.leanback.widget.ListRow
import android.support.v17.leanback.widget.ListRowPresenter
import com.parthdave93.tvdemo.core.ApplicationClass
import com.parthdave93.tvdemo.di.networkpresenters.cardwithheader.CardWithHeaderPresenterContract
import javax.inject.Inject

/**
 * Created by Parth Dave on 07-08-2018.
 */

class Tutorial1Fragment : BrowseSupportFragment() {

    @Inject
    lateinit var presenter: CardWithHeaderPresenterContract.CardWithHeaderPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity?.application as ApplicationClass)?.presenterComponent.inject(this)

        setupTitleAndBackgroundColor()
        setUpAdapters()
    }

    private fun setUpAdapters() {
        var rowAdapter = ArrayObjectAdapter(ListRowPresenter())

        presenter.fetchLists().observe(this, Observer {
            it?.data?.let {
                for ((index, model) in it.withIndex()) {
                    var headerItem = HeaderItem(index.toLong(), model.title)
                    var innerListAdapter = ArrayObjectAdapter(GridItemPresenter())
                    model.cards?.let {
                        innerListAdapter.addAll(0, it)
                    }
                    rowAdapter.add(ListRow(headerItem, innerListAdapter))
                }
            }
        })
        adapter = rowAdapter
    }

    private fun setupTitleAndBackgroundColor() {
        title = "Tutorial 1"
        headersState = HEADERS_ENABLED
        brandColor = Color.GRAY
    }


}
