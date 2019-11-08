package com.alexandrpershin.spacexlaunches.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexandrpershin.spacexlaunches.repository.RocketLaunchesRepository
import com.alexandrpershin.spacexlaunches.ui.activity.RocketLaunchesViewModel

class ViewModelFactory(
    private var rocketLaunchesRepository: RocketLaunchesRepository? = null
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            //RocketLaunchesViewModel
            modelClass.isAssignableFrom(RocketLaunchesViewModel::class.java) -> {
                requireNotNull(rocketLaunchesRepository) { "rocketLaunchesRepository couldn't be null" }

                RocketLaunchesViewModel(rocketLaunchesRepository!!) as T
            }

            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}

