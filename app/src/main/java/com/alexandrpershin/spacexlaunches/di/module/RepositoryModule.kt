package com.alexandrpershin.spacexlaunches.di.module

import com.alexandrpershin.spacexlaunches.api.RocketLaunchesApi
import com.alexandrpershin.spacexlaunches.persistence.RocketLaunchesDao
import com.alexandrpershin.spacexlaunches.repository.RocketLaunchesRepository
import com.alexandrpershin.spacexlaunches.utils.RocketLaunchesMapper
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService

@Module
class RepositoryModule {

    @Provides
    fun provideRocketLaunchesRepository(
        api: RocketLaunchesApi,
        dao: RocketLaunchesDao,
        mapper: RocketLaunchesMapper,
        executor: ExecutorService
    ): RocketLaunchesRepository {
        return RocketLaunchesRepository(api, dao, mapper, executor)
    }


    @Provides
    fun provideRocketLaunchesMapper(): RocketLaunchesMapper {
        return RocketLaunchesMapper()
    }
}