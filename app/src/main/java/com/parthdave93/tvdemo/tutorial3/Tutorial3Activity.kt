package com.parthdave93.tvdemo.tutorial3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.parthdave93.tvdemo.R
import com.parthdave93.tvdemo.model.Movie

/**
 * Created by Parth Dave on 08-08-2018.
 */
class Tutorial3Activity : FragmentActivity(){

    companion object {
        fun start(context : Context,movie: Movie){
            var intent = Intent(context,Tutorial3Activity::class.java)
            intent.putExtra("movie",movie)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial3)
    }
}