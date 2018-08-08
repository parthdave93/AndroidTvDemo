package com.parthdave93.tvdemo.normal_tutorial.ui

import android.graphics.drawable.Drawable
import android.support.v17.leanback.app.BackgroundManager
import android.support.v4.app.FragmentActivity
import com.parthdave93.tvdemo.R

/**
 * Created by Parth Dave on 25-07-2018.
 */
class SimpleBackgroundManager {

    var defaultBackground = R.drawable.default_background
    var mBackgroundManager: BackgroundManager? = null
    var mDefaultBackground: Drawable? = null

    constructor(activity: FragmentActivity?) {
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager?.attach(activity?.window)
        mDefaultBackground = activity?.getDrawable(defaultBackground)
    }

    fun updateBackground(drawable: Drawable) {
        mBackgroundManager?.drawable = drawable
    }

    fun clearBackground() {
        mBackgroundManager?.drawable = mDefaultBackground

    }

    fun release(){
        mBackgroundManager?.release()
    }

}