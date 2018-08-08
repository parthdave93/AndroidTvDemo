package com.parthdave93.tvdemo.di.components

import com.parthdave93.di.modules.NetworkModule
import com.parthdave93.tvdemo.di.networkrepository.NetworkRepository
import com.parthdave93.tvdemo.di.scopes.Singleton
import dagger.Component

/**
 * Created by Parth Dave on 07-08-2018.
 */
@Component(dependencies = [ContextComponent::class], modules = [NetworkModule::class])
@Singleton
interface NetworkComponent {

    fun networkRepo(): NetworkRepository

}