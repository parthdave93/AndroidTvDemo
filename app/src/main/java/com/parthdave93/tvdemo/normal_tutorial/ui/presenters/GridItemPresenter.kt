package com.parthdave93.tvdemo.normal_tutorial.ui.presenters

import android.support.v17.leanback.widget.Presenter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.parthdave93.tvdemo.R
import com.parthdave93.tvdemo.model.CardItemModel
import kotlinx.android.synthetic.main.item_card.view.*

/**
 * Created by Parth Dave on 25-07-2018.
 */
class GridItemPresenter : Presenter(){

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_card, parent, false)
        return CardItemHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        var holder = viewHolder as CardItemHolder
        var itemObject = item as CardItemModel
        holder.tvDescription.text = itemObject.description
        holder.tvTitle.text = itemObject.title

        itemObject.registerTitleChange = object: CardItemModel.RegisterChange<String> {
            override fun onValueChanged(value: String) {
                holder.tvTitle.text = value
            }
        }


        itemObject.registerDescriptionChange = object: CardItemModel.RegisterChange<String> {
            override fun onValueChanged(value: String) {
                holder.tvDescription.text = value
            }
        }

        holder.view.isFocusable = true;
        holder.view.isFocusableInTouchMode = true;
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }


    class CardItemHolder(view: View): ViewHolder(view){
        var tvTitle = view.tvTitle
        var tvDescription = view.tvDescription
    }
}