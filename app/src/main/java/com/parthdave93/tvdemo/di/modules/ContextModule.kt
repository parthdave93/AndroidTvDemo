package com.parthdave93.tvdemo.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Parth Dave on 07-08-2018.
 */
@Module
class ContextModule(val context: Context){

    @Provides
    fun providesContext(): Context{
        return context
    }
}