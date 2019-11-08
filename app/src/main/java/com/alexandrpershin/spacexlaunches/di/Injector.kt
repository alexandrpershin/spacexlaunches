package com.alexandrpershin.spacexlaunches.di

import com.alexandrpershin.spacexlaunches.MyApplication
import com.alexandrpershin.spacexlaunches.di.component.AppComponent
import com.alexandrpershin.spacexlaunches.di.component.DaggerAppComponent
import com.alexandrpershin.spacexlaunches.di.component.DaggerMainActivityComponent
import com.alexandrpershin.spacexlaunches.di.component.MainActivityComponent
import com.alexandrpershin.spacexlaunches.di.module.AppModule
import com.alexandrpershin.spacexlaunches.di.module.MainActivityModule
import com.alexandrpershin.spacexlaunches.ui.activity.MainActivity

/**
 * This is helper class for dependency injection
 **/

class Injector private constructor() {

    private var activityComponent: MainActivityComponent? = null
    private var appComponent: AppComponent? = null

    companion object {
        val instance: Injector by lazy {
            Injector()
        }
    }

    fun initialiseAppComponent(application: MyApplication) {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build()
        }
        appComponent!!.inject(application)
    }

    fun initMainActivityComponent(activity: MainActivity) {
        if (activityComponent == null) {
            activityComponent = DaggerMainActivityComponent
                .builder()
                .mainActivityModule(MainActivityModule(activity))
                .appComponent(appComponent)
                .build()
        }
        activityComponent?.inject(activity = activity)
    }

    fun releaseMainActivityComponent() {
        activityComponent = null
    }


}