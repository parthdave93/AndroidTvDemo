package com.parthdave93.tvdemo.tutorial3

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v17.leanback.app.DetailsSupportFragment
import android.support.v17.leanback.widget.*
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.parthdave93.tvdemo.model.Movie
import com.parthdave93.tvdemo.normal_tutorial.ui.SimpleBackgroundManager
import com.parthdave93.tvdemo.tutorial2.CardPresenter
import com.parthdave93.tvdemo.tutorial4.ErrorActivity


/**
 * Created by Parth Dave on 08-08-2018.
 */
class Tutorial3Fragment : DetailsSupportFragment() {

    lateinit var mCustomFullWidthOverviewPresenter: CustomFullWidthOverviewPresenter
    val DETAIL_THUMB_WIDTH = 1000
    val DETAIL_THUMB_HEIGHT = 500
    lateinit var movie: Movie

    var simpleBackgroundManager: SimpleBackgroundManager? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        simpleBackgroundManager = SimpleBackgroundManager(activity)
        mCustomFullWidthOverviewPresenter = CustomFullWidthOverviewPresenter(CustomDetailPresenter())
        movie = activity?.intent?.getSerializableExtra("movie") as Movie
        var detailOverviewRow = DetailsOverviewRow(movie)
        updateImageWhenReady(detailOverviewRow)
        var sparseArray = SparseArrayObjectAdapter()
        for (i in 0..5) {
            sparseArray.set(i, Action(i.toLong(), "label 1", "label 2"))
        }
        detailOverviewRow.actionsAdapter = sparseArray

        var listRowAdapter = ArrayObjectAdapter(CardPresenter())
        listRowAdapter.addAll(0, movie.similarMovie)

        var header = HeaderItem(1.toLong(), "Related Videos")

        var classSelectorPresenter = ClassPresenterSelector()
        mCustomFullWidthOverviewPresenter.initialState = FullWidthDetailsOverviewRowPresenter.STATE_SMALL;
        classSelectorPresenter.addClassPresenter(DetailsOverviewRow::class.java, mCustomFullWidthOverviewPresenter)
        classSelectorPresenter.addClassPresenter(ListRow::class.java, ListRowPresenter())

        var objectADapter = ArrayObjectAdapter(classSelectorPresenter)
        objectADapter.add(detailOverviewRow)
        objectADapter.add(ListRow(header, listRowAdapter))
        adapter = objectADapter

        onItemViewClickedListener = ClickedListener()
    }

    inner class ClickedListener : OnItemViewClickedListener{
        override fun onItemClicked(p0: Presenter.ViewHolder?, p1: Any?, p2: RowPresenter.ViewHolder?, p3: Row?) {
            ErrorActivity.start(activity!!)
        }
    }


    inner class CustomFullWidthOverviewPresenter(innerPresenter: Presenter) : FullWidthDetailsOverviewRowPresenter(innerPresenter) {

        override fun onBindRowViewHolder(holder: RowPresenter.ViewHolder?, item: Any?) {
            super.onBindRowViewHolder(holder, item)
            this.setState(holder as FullWidthDetailsOverviewRowPresenter.ViewHolder, FullWidthDetailsOverviewRowPresenter.STATE_SMALL)
        }
    }

    inner class CustomDetailPresenter : AbstractDetailsDescriptionPresenter() {
        override fun onBindDescription(holder: ViewHolder?, obj: Any?) {
            var movie = obj as Movie
            holder?.title?.text = movie.title
            holder?.body?.text = movie.description
            holder?.subtitle?.text = movie.producer
        }
    }

    fun updateImageWhenReady(row: DetailsOverviewRow) {
        Glide.with(activity!!).load(movie.imageUrl).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                simpleBackgroundManager?.clearBackground()
                e?.printStackTrace()
                return true
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                resource?.let {
//                    row.setImageBitmap(activity, (it as BitmapDrawable).bitmap)
                    row.imageDrawable = it
//                    simpleBackgroundManager?.updateBackground(it)
                }
                Log.d("tag", "found")
                return true
            }
        }).submit(DETAIL_THUMB_WIDTH, DETAIL_THUMB_HEIGHT)
    }

    override fun onDetach() {
        simpleBackgroundManager?.release()
        super.onDetach()
    }
}