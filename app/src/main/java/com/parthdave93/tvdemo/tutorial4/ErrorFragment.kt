package com.parthdave93.tvdemo.tutorial4

import android.os.Bundle
import android.support.v17.leanback.app.ErrorSupportFragment
import android.view.View
import com.parthdave93.tvdemo.R

/**
 * Created by Parth Dave on 08-08-2018.
 */
class ErrorFragment : ErrorSupportFragment(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        title = "Error Fragment"
        imageDrawable = activity?.getDrawable(R.drawable.lb_ic_loop)
        message = "Error Message"
        setDefaultBackground(true)

        buttonText = "Dismiss"
        buttonClickListener = View.OnClickListener{
            activity!!.finish()
        }
    }

}