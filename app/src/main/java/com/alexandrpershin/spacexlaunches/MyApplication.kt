package com.alexandrpershin.spacexlaunches

import android.app.Application
import com.alexandrpershin.spacexlaunches.di.Injector

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.instance.initialiseAppComponent(this)
    }

}