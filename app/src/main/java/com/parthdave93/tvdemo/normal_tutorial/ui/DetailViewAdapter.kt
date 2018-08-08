package com.parthdave93.tvdemo.normal_tutorial.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.parthdave93.tvdemo.R
import com.parthdave93.tvdemo.model.CardItemModel
import kotlinx.android.synthetic.main.item_card.view.*


/**
 * Created by Parth Dave on 25-07-2018.
 */
class DetailViewAdapter : RecyclerView.Adapter<DetailViewAdapter.ViewHolder> {
    var items: List<CardItemModel>? = null
    var layoutInflater: LayoutInflater

    constructor(context: Context, items: List<CardItemModel>) {
        this.items = items
        layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(R.layout.item_card, null, false))
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: DetailViewAdapter.ViewHolder, position: Int) {
        var item = items?.get(position)
        holder.tvTitle.text = item?.title
        holder.tvDescription.text = item?.description

        holder.itemView.isFocusable = true
        holder.itemView.isFocusableInTouchMode = true

        /*if(item!!.isFocused){
            val animation = ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f)
            animation.fillAfter = true
            holder.itemView.startAnimation(animation)
        }else{
            val animation = ScaleAnimation(1f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f)
            animation.fillAfter = true
            holder.itemView.startAnimation(animation)
        }*/
       /* holder.itemView.onFocusChangeListener = View.OnFocusChangeListener { view, focus ->
            if(focus){
                val animation = ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f)
                animation.fillAfter = true
                holder.itemView.startAnimation(animation)
            }
        }*/
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle = itemView.tvTitle
        var tvDescription = itemView.tvDescription
    }
}