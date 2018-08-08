package com.parthdave93.tvdemo.normal_tutorial.ui

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.support.v17.leanback.app.BrowseSupportFragment
import android.support.v17.leanback.widget.*
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.parthdave93.tvdemo.R
import com.parthdave93.tvdemo.model.CardItemModel
import com.parthdave93.tvdemo.normal_tutorial.ui.presenters.GridItemPresenter


/**
 * Created by Parth Dave on 24-07-2018.
 */
class MainFragment : BrowseSupportFragment() {

    var rowsAdapter: ArrayObjectAdapter? = null
    var simpleBackgroundManager: SimpleBackgroundManager? = null

    var HEADER1_POS: Long = 1;
    var HEADER1_TITLE = "Page 1";
    var HEADER2_POS: Long = 2;
    var HEADER2_TITLE = "Page 2";
    var HEADER3_POS: Long = 3;
    var HEADER3_TITLE = "Page 3";
    var HEADER4_POS: Long = 4;
    var HEADER4_TITLE = "Page 4";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpElements()

        loadRows()

        setUpListeners()

        setUpBackground()

        mainFragmentRegistry.registerFragment(PageRow::class.java, PageRowFragmentFactory())
    }

    private fun setUpBackground() {
        simpleBackgroundManager = SimpleBackgroundManager(activity)
    }

    private fun setUpListeners() {
//        onItemViewSelectedListener = CardItemSelectionListener()
//        onItemViewClickedListener = CardItemClickListener()
    }

    private fun loadRows() {
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = rowsAdapter

        var headerItem1 = HeaderItem(HEADER1_POS, HEADER1_TITLE)
        var page1Row1 = PageRow(headerItem1)
        rowsAdapter?.add(page1Row1)


        var headerItem2 = HeaderItem(HEADER2_POS, HEADER2_TITLE)
        var page1Row2 = PageRow(headerItem2)
        rowsAdapter?.add(page1Row2)


        var headerItem3 = HeaderItem(HEADER3_POS, HEADER3_TITLE)
        var page1Row3 = PageRow(headerItem3)
        rowsAdapter?.add(page1Row3)


        var headerItem4 = HeaderItem(HEADER4_POS, HEADER4_TITLE)
        var page1Row4 = PageRow(headerItem4)
        rowsAdapter?.add(page1Row4)
    }

    private fun startUpdates(itemToChange: CardItemModel, counter: Int) {
        Handler().postDelayed({
            itemToChange.title = "Title $counter"
            itemToChange.description = "Description $counter"
            startUpdates(itemToChange, counter + 1)
        }, 2000)
    }

    private fun setUpElements() {
        title = "Test Title"
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = Color.RED
        searchAffordanceColor = Color.GREEN
    }

    inner class CardItemSelectionListener : OnItemViewSelectedListener {
        override fun onItemSelected(itemViewHolder: Presenter.ViewHolder?, item: Any?, rowViewHolder: RowPresenter.ViewHolder?, row: Row?) {
            item?.let {
                Glide.with(activity!!).load((it as CardItemModel).backgroundImageUrl).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        simpleBackgroundManager?.clearBackground()
                        e?.printStackTrace()
                        return true
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        resource?.let { simpleBackgroundManager?.updateBackground(it) }
                        return true
                    }
                }).submit()
            }
        }
    }

    inner class CardItemClickListener : OnItemViewClickedListener {
        override fun onItemClicked(itemViewHolder: Presenter.ViewHolder?, item: Any?, rowViewHolder: RowPresenter.ViewHolder?, row: Row?) {
            item?.let {
                DetailActivity.starter(activity, it as CardItemModel)
            }
        }
    }


    inner class PageRowFragmentFactory : BrowseSupportFragment.FragmentFactory<Fragment>() {

        override fun createFragment(p0: Any?): Fragment {
            var row = p0 as Row
            if (row.headerItem.id == HEADER1_POS) {
                return Page1Fragment()
            }
            return Page1Fragment()
        }
    }

    class Page1Fragment : Fragment(), BrowseSupportFragment.MainFragmentAdapterProvider {

        var mMainFragmentAdapter: BrowseSupportFragment.MainFragmentAdapter<Fragment> = object : BrowseSupportFragment.MainFragmentAdapter<Fragment>(this) {

        }
        private val ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_SMALL
        lateinit var mGridPresenter: VerticalGridPresenter

        override fun getMainFragmentAdapter(): MainFragmentAdapter<*> {
            return mMainFragmentAdapter
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_pages, null, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            mGridPresenter = VerticalGridPresenter(ZOOM_FACTOR)
            mGridPresenter.numberOfColumns = 4
            var rowsAdapter = ArrayObjectAdapter(CustomPresenterSelector())

            val itemToChange = CardItemModel("Title 1", "Description 1", "http://cdn.wonderfulengineering.com/wp-content/uploads/2016/01/Desktop-Wallpaper-4-768x480.jpg")
            rowsAdapter.add(itemToChange)
            rowsAdapter.add(CardItemModel("Title 3", "Description 3", "https://wallpaperbrowse.com/media/images/eiffel-tower-wallpaper-18_fRZLW4V.jpg"))
            rowsAdapter.add(CardItemModel("Title 2", "Description 2", "http://cdn.wonderfulengineering.com/wp-content/uploads/2016/01/Desktop-Wallpaper-4-768x480.jpg"))


            var verticalAdapter = ArrayObjectAdapter()
            verticalAdapter.add(ListRow(HeaderItem(0, "title"), rowsAdapter))

            val gridDock = view.findViewById(R.id.browse_grid_dock) as ViewGroup
            var mGridViewHolder = mGridPresenter.onCreateViewHolder(gridDock)
            gridDock.addView(mGridViewHolder.view)

            mGridPresenter.onBindViewHolder(mGridViewHolder, verticalAdapter)

            mainFragmentAdapter.fragmentHost.notifyViewCreated(mMainFragmentAdapter)
        }
    }

    class CustomPresenterSelector : PresenterSelector() {
        override fun getPresenter(p0: Any): Presenter {
            return GridItemPresenter()
        }
    }
}
