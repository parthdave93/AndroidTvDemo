package com.parthdave93.tvdemo.di.components

import com.parthdave93.tvdemo.di.PresentersModule
import com.parthdave93.tvdemo.di.scopes.ActivityScope
import com.parthdave93.tvdemo.tutorial1.Tutorial1Fragment
import com.parthdave93.tvdemo.tutorial2.Tutorial2Fragment
import dagger.Component

/**
 * Created by Parth Dave on 07-08-2018.
 */

@Component(dependencies = [NetworkComponent::class],modules = [PresentersModule::class])
@ActivityScope
interface PresenterComponent {
    fun inject(tutorial1Fragment: Tutorial1Fragment)
    fun inject(tutorial2Fragment: Tutorial2Fragment)
}
