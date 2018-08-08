package com.parthdave93.tvdemo.core

import android.support.multidex.MultiDexApplication
import com.parthdave93.di.modules.NetworkModule
import com.parthdave93.tvdemo.di.PresentersModule
import com.parthdave93.tvdemo.di.components.DaggerContextComponent
import com.parthdave93.tvdemo.di.components.DaggerNetworkComponent
import com.parthdave93.tvdemo.di.components.DaggerPresenterComponent
import com.parthdave93.tvdemo.di.components.PresenterComponent
import com.parthdave93.tvdemo.di.modules.ContextModule

/**
 * Created by Parth Dave on 26-07-2018.
 */
class ApplicationClass: MultiDexApplication(){

    lateinit var presenterComponent: PresenterComponent

    override fun onCreate() {
        super.onCreate()

        var contextComponent = DaggerContextComponent.builder().contextModule(ContextModule(this)).build()
        var networkComponent = DaggerNetworkComponent.builder().networkModule(NetworkModule(this)).contextComponent(contextComponent).build()
        presenterComponent = DaggerPresenterComponent.builder().networkComponent(networkComponent).presentersModule(PresentersModule()).build()
    }

}