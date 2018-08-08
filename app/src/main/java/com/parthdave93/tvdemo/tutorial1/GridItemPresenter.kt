package com.parthdave93.tvdemo.tutorial1

import android.support.v17.leanback.widget.Presenter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.parthdave93.tvdemo.R
import com.parthdave93.tvdemo.model.CardItemModel
import kotlinx.android.synthetic.main.item_card.view.*

/**
 * Created by Parth Dave on 07-08-2018.
 */
class GridItemPresenter : Presenter(){


    override fun onCreateViewHolder(viewGroup: ViewGroup?): ViewHolder {
        var view = LayoutInflater.from(viewGroup?.context).inflate(R.layout.item_card, viewGroup, false)
        return CardItemHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, obj: Any?) {
        var holder = viewHolder as GridItemPresenter.CardItemHolder
        var itemObject = obj as CardItemModel
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

    override fun onUnbindViewHolder(holder: ViewHolder?) {

    }

    class CardItemHolder(view: View): ViewHolder(view){
        var tvTitle = view.tvTitle
        var tvDescription = view.tvDescription
    }
}