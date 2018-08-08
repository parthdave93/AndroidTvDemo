package com.parthdave93.tvdemo.normal_tutorial.ui.login

import android.os.Bundle
import android.support.v17.leanback.app.GuidedStepSupportFragment
import android.support.v4.app.FragmentActivity

/**
 * Created by Parth Dave on 26-07-2018.
 */
class LoginActivity : FragmentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment = LoginFragment()
        fragment.setArguments(intent.extras) // Delegate Movie to first step.
        GuidedStepSupportFragment.addAsRoot(this, fragment, android.R.id.content)
    }
}