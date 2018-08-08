package com.parthdave93.tvdemo.di.components

import android.content.Context
import com.parthdave93.tvdemo.di.modules.ContextModule
import dagger.Component

/**
 * Created by Parth Dave on 07-08-2018.
 */

@Component(modules = [ContextModule::class])
interface ContextComponent {

    fun context(): Context
}