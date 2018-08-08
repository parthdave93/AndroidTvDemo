package com.parthdave93.tvdemo.tutorial4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.parthdave93.tvdemo.R

/**
 * Created by Parth Dave on 08-08-2018.
 */

class ErrorActivity : FragmentActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ErrorActivity::class.java))
        }
    }

    private var mErrorFragment: ErrorFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
        mErrorFragment = ErrorFragment();

        supportFragmentManager.beginTransaction().add(R.id.flReplaceFragment, mErrorFragment!!).commit()
    }
}
