package com.parthdave93.tvdemo.normal_tutorial.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.KeyEvent
import com.bumptech.glide.Glide
import com.parthdave93.tvdemo.R
import com.parthdave93.tvdemo.model.CardItemModel
import com.parthdave93.tvdemo.utils.SpacesItemDecoration
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by Parth Dave on 25-07-2018.
 */
class DetailActivity : FragmentActivity() {

    var cardItemModel: CardItemModel? = null
    var detailViewAdapter: DetailViewAdapter? = null

    var lastRecyclerViewItemFocused = 0

    companion object {
        fun starter(activity: FragmentActivity?, cardItemModel: CardItemModel) {
            var intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra("model", cardItemModel)
            activity?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        cardItemModel = intent.getSerializableExtra("model") as CardItemModel?

        Glide.with(this).load(cardItemModel?.backgroundImageUrl).into(ivBackground)

        tvTitle.text = cardItemModel?.title
        tvDescription.text = cardItemModel?.description

//        rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvList.addItemDecoration(SpacesItemDecoration(20))
        detailViewAdapter = DetailViewAdapter(this, generateList())
        rvList.adapter = detailViewAdapter
    }

    private fun generateList(): List<CardItemModel> {
        var list = ArrayList<CardItemModel>()
        list.add(CardItemModel("Title 1", "Description 1", "http://cdn.wonderfulengineering.com/wp-content/uploads/2016/01/Desktop-Wallpaper-4-768x480.jpg"))
        list.add(CardItemModel("Title 2", "Description 2", "http://cdn.wonderfulengineering.com/wp-content/uploads/2016/01/Desktop-Wallpaper-4-768x480.jpg"))
        list.add(CardItemModel("Title 3", "Description 3", "http://cdn.wonderfulengineering.com/wp-content/uploads/2016/01/Desktop-Wallpaper-4-768x480.jpg"))
        list.add(CardItemModel("Title 4", "Description 4", "http://cdn.wonderfulengineering.com/wp-content/uploads/2016/01/Desktop-Wallpaper-4-768x480.jpg"))
        list.add(CardItemModel("Title 5", "Description 5", "http://cdn.wonderfulengineering.com/wp-content/uploads/2016/01/Desktop-Wallpaper-4-768x480.jpg"))

        return list
    }


    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        Log.d("tag","lastRecyclerViewItemFocused:$lastRecyclerViewItemFocused")
        when (event?.keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                Log.d("tag", "enter pressed")
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                if (lastRecyclerViewItemFocused == 0)
                    finish()
                else {
                    detailViewAdapter?.items?.get(lastRecyclerViewItemFocused)?.isFocused = false
                    detailViewAdapter?.notifyItemChanged(lastRecyclerViewItemFocused)
                    lastRecyclerViewItemFocused--
                    detailViewAdapter?.items?.get(lastRecyclerViewItemFocused)?.isFocused = true
                    detailViewAdapter?.notifyItemChanged(lastRecyclerViewItemFocused)
                }
                return true
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                if (lastRecyclerViewItemFocused == detailViewAdapter?.itemCount)
                    finish()
                else {
                    detailViewAdapter?.items?.get(lastRecyclerViewItemFocused)?.isFocused = false
                    detailViewAdapter?.notifyItemChanged(lastRecyclerViewItemFocused)
                    lastRecyclerViewItemFocused++
                    detailViewAdapter?.items?.get(lastRecyclerViewItemFocused)?.isFocused = true
                    detailViewAdapter?.notifyItemChanged(lastRecyclerViewItemFocused)
                }
                return true
            }
           /* KeyEvent.KEYCODE_DPAD_DOWN -> {
                rvList.getChildAt(lastRecyclerViewItemFocused).requestFocus()
                rvList.smoothScrollToPosition(lastRecyclerViewItemFocused)
                return true
            }*/
        }
        return super.dispatchKeyEvent(event)
    }
}

