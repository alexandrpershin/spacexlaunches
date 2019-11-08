package com.alexandrpershin.spacexlaunches.di.component

import com.alexandrpershin.spacexlaunches.di.module.MainActivityModule
import com.alexandrpershin.spacexlaunches.di.scope.MainActivityScope
import com.alexandrpershin.spacexlaunches.ui.activity.MainActivity
import dagger.Component


@MainActivityScope
@Component(dependencies = [AppComponent::class], modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(activity: MainActivity)
}
