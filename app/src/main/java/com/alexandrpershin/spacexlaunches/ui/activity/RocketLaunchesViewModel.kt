package com.alexandrpershin.spacexlaunches.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alexandrpershin.spacexlaunches.model.RocketLaunchDbEntity
import com.alexandrpershin.spacexlaunches.repository.RocketLaunchesRepository
import com.alexandrpershin.spacexlaunches.ui.state.ScreenState

class RocketLaunchesViewModel(private val repository: RocketLaunchesRepository) : ViewModel() {

    /**
     * [refreshDataAction] - is callback which will be invoked when user will perform swipe to refresh action
     * */

    var refreshDataAction = {
        fetchAllRocketLaunches()
    }

    var rocketLaunchesLiveData: LiveData<List<RocketLaunchDbEntity>> =
        repository.getRocketLaunches()

    var statusLiveData: LiveData<ScreenState> = repository.statusLiveData

    fun filterRocketLaunchesByTbd(tbdEnabled: Boolean) {
        repository.filterRocketLaunchesByTbd(tbdEnabled)

        updateRefreshDataAction { repository.filterRocketLaunchesByTbd(tbdEnabled) }
    }

    fun fetchAllRocketLaunches() {
        repository.fetchAllRocketLaunches()

        updateRefreshDataAction { repository.fetchAllRocketLaunches() }
    }

    fun getRocketDetails(launchId: Long): LiveData<RocketLaunchDbEntity> {
        return repository.getRocketDetails(launchId)
    }

    /**
     * Sets new callback for [refreshDataAction] which will be invoked on user refresh data action
     * */
    private fun updateRefreshDataAction(callback: () -> Unit) {
        refreshDataAction = callback
    }

    override fun onCleared() {
        super.onCleared()
        repository.dispose()
    }

}