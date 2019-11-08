package com.alexandrpershin.spacexlaunches.di.module

import androidx.lifecycle.ViewModelProvider
import com.alexandrpershin.spacexlaunches.repository.RocketLaunchesRepository
import com.alexandrpershin.spacexlaunches.ui.activity.MainActivity
import com.alexandrpershin.spacexlaunches.ui.activity.RocketLaunchesViewModel
import com.alexandrpershin.spacexlaunches.utils.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val activity: MainActivity?) {

    /**
     * Creates ViewModel for [com.alexandrpershin.spacexlaunches.ui.activity.MainActivity]
     * */

    @Provides
    fun provideMainActivityViewModel(repository: RocketLaunchesRepository): RocketLaunchesViewModel {
        return ViewModelProvider(
            activity!!,
            ViewModelFactory(repository)
        ).get(RocketLaunchesViewModel::class.java)
    }

}