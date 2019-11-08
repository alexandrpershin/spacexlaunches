package com.alexandrpershin.spacexlaunches.di.module

import android.content.Context
import com.alexandrpershin.spacexlaunches.persistence.LocalDatabase
import com.alexandrpershin.spacexlaunches.persistence.RocketLaunchesDao
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): LocalDatabase {
        return LocalDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideRocketLaunchesDao(localDatabase: LocalDatabase): RocketLaunchesDao {
        return localDatabase.rocketLaunchesDao()
    }

    @Provides
    fun provideExecutor(): ExecutorService = Executors.newSingleThreadExecutor()
}