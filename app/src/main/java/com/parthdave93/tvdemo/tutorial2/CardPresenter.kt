package com.parthdave93.tvdemo.tutorial2

import android.graphics.Color
import android.support.v17.leanback.widget.ImageCardView
import android.support.v17.leanback.widget.Presenter
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.parthdave93.tvdemo.model.Movie
import com.parthdave93.tvdemo.model.SimilarMovie


/**
 * Created by Parth Dave on 07-08-2018.
 */
class CardPresenter : Presenter() {

    private val CARD_WIDTH = 313
    private val CARD_HEIGHT = 176

    override fun onCreateViewHolder(viewGroup: ViewGroup?): ViewHolder {
        var imageCardView = ImageCardView(viewGroup?.context)
        imageCardView.isFocusableInTouchMode = true
        imageCardView.setBackgroundColor(Color.GRAY)
        return CardViewHolder(imageCardView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, obj: Any?) {
        var cardHolder = holder as CardViewHolder
        if(obj is Movie) {
            var movieObject = obj as Movie
            cardHolder.imageCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
            cardHolder.imageCardView.titleText = movieObject.title
            cardHolder.imageCardView.contentText = movieObject.description
            Glide.with(holder.view.context).load(movieObject.imageUrl).into(holder.imageCardView.mainImageView)
        }else if(obj is SimilarMovie){
            var movieObject = obj as SimilarMovie
            cardHolder.imageCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
            cardHolder.imageCardView.titleText = movieObject.title
            cardHolder.imageCardView.contentText = movieObject.description
            Glide.with(holder.view.context).load(movieObject.imageUrl).into(holder.imageCardView.mainImageView)
        }
    }

    override fun onUnbindViewHolder(holder: ViewHolder?) {

    }

    class CardViewHolder(private val itemView : ImageCardView) : Presenter.ViewHolder(itemView){
        var imageCardView : ImageCardView = itemView
    }
}