package com.parthdave93.tvdemo.tutorial2

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v17.leanback.app.BackgroundManager
import android.support.v17.leanback.app.BrowseSupportFragment
import android.support.v17.leanback.widget.*
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.parthdave93.tvdemo.core.ApplicationClass
import com.parthdave93.tvdemo.di.networkpresenters.movie.MoviePresenterContract
import com.parthdave93.tvdemo.model.Movie
import com.parthdave93.tvdemo.normal_tutorial.ui.SimpleBackgroundManager
import com.parthdave93.tvdemo.tutorial3.Tutorial3Activity
import javax.inject.Inject


/**
 * Created by Parth Dave on 07-08-2018.
 */
class Tutorial2Fragment : BrowseSupportFragment() {

    @Inject
    lateinit var presenter: MoviePresenterContract.MoviePresenter

    var simpleBackgroundManager: SimpleBackgroundManager? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity?.application as ApplicationClass).presenterComponent.inject(this)

        setUpTitles()
        loadData()
        setUpListeners()
        simpleBackgroundManager = SimpleBackgroundManager(activity)
    }

    private fun setUpListeners() {

    }

    private fun loadData() {
        var rowAdapter = ArrayObjectAdapter(ListRowPresenter())

        presenter.fetchLists().observe(this, Observer {
            it?.data?.let {
                for (header in it) {
                    var headerItem = HeaderItem(header.title)
                    var arrayObject = ArrayObjectAdapter(CardPresenter())
                    header.cards?.let {
                        arrayObject.addAll(0, it)
                    }
                    rowAdapter.add(ListRow(headerItem, arrayObject))
                }
            }
        })

        adapter = rowAdapter

        onItemViewSelectedListener = ItemSelectionListener()
        onItemViewClickedListener = ItemClickListener()
    }

    private fun setUpTitles() {
        title = "Tutorial 2"
        headersState = HEADERS_ENABLED
        brandColor = Color.BLUE
    }

    inner class ItemSelectionListener : OnItemViewSelectedListener {
        override fun onItemSelected(itemViewHolder: Presenter.ViewHolder?, obj: Any?, rowVIewHolder: RowPresenter.ViewHolder?, row: Row?) {
            if (obj == null)
                return
            var movie: Movie = obj as Movie
            var backgroundManager = BackgroundManager.getInstance(activity)
            activity?.let {
                Glide.with(it).load(movie.imageUrl).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        simpleBackgroundManager?.clearBackground()
                        e?.printStackTrace()
                        return true
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        resource?.let { simpleBackgroundManager?.updateBackground(it) }
                        Log.d("tag", "found")
                        return true
                    }
                }).submit()
            }
        }
    }


    inner class ItemClickListener : OnItemViewClickedListener {
        override fun onItemClicked(itemViewHolder: Presenter.ViewHolder?, obj: Any?, rowVIewHolder: RowPresenter.ViewHolder?, row: Row?) {
            if (obj == null)
                return
            var movie: Movie = obj as Movie
            activity?.let { Tutorial3Activity.start(it,movie) }
        }
    }


    override fun onDetach() {
        simpleBackgroundManager?.release()
        super.onDetach()
    }
}
