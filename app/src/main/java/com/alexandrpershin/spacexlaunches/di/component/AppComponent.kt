package com.alexandrpershin.spacexlaunches.di.component

import android.content.Context
import com.alexandrpershin.spacexlaunches.MyApplication
import com.alexandrpershin.spacexlaunches.di.module.ApiModule
import com.alexandrpershin.spacexlaunches.di.module.AppModule
import com.alexandrpershin.spacexlaunches.di.module.DatabaseModule
import com.alexandrpershin.spacexlaunches.di.module.RepositoryModule
import com.alexandrpershin.spacexlaunches.persistence.LocalDatabase
import com.alexandrpershin.spacexlaunches.repository.RocketLaunchesRepository
import dagger.Component
import javax.inject.Singleton


/**
 * [AppComponent] is application component for dependency injection using library Dagger2
 * */

@Singleton
@Component(
    modules = [AppModule::class, RepositoryModule::class, DatabaseModule::class, ApiModule::class]
)
interface AppComponent {
    fun inject(application: MyApplication)

    fun provideContext(): Context

    fun provideRocketLaunchesRepository(): RocketLaunchesRepository
}