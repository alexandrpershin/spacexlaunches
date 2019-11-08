package com.alexandrpershin.spacexlaunches.di.module

import android.content.Context
import com.alexandrpershin.spacexlaunches.api.RocketLaunchesApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRocketLaunchesApi(context: Context): RocketLaunchesApi {
        return RocketLaunchesApi.create(context)
    }
}