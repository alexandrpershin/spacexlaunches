package com.alexandrpershin.spacexlaunches.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexandrpershin.spacexlaunches.api.RocketLaunchesApi
import com.alexandrpershin.spacexlaunches.model.RocketLaunchDbEntity
import com.alexandrpershin.spacexlaunches.persistence.RocketLaunchesDao
import com.alexandrpershin.spacexlaunches.ui.state.ScreenState
import com.alexandrpershin.spacexlaunches.utils.RocketLaunchesMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import java.util.concurrent.Executor
import javax.inject.Singleton


@Singleton
class RocketLaunchesRepository(
    private val rocketLaunchesApi: RocketLaunchesApi,
    private val rocketLaunchesDao: RocketLaunchesDao,
    private val mapper: RocketLaunchesMapper,
    private val executor: Executor
) {

    private var disposable: CompositeDisposable? = null

    val statusLiveData = MutableLiveData<ScreenState>()

    fun getRocketLaunches(): LiveData<List<RocketLaunchDbEntity>> {
        return rocketLaunchesDao.getAllLaunches()
    }

    fun getRocketDetails(launchId: Long): LiveData<RocketLaunchDbEntity> {
        return rocketLaunchesDao.getRocketLaunchById(launchId)
    }

    fun filterRocketLaunchesByTbd(tbdEnabled: Boolean) {
        statusLiveData.postValue(ScreenState.Loading)
        val request =
            rocketLaunchesApi.filterRocketLaunchesByTbd(tbdEnabled).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    statusLiveData.postValue(ScreenState.Success)
                    result?.let {
                        executor.execute {
                            rocketLaunchesDao.updateData(result.map {
                                mapper.toRocketLaunchDbEntity(
                                    it
                                )
                            })
                        }
                    }
                }) { e ->
                    if (e is UnknownHostException) {
                        statusLiveData.postValue(ScreenState.InternetError)
                    } else {
                        statusLiveData.postValue(ScreenState.Error(throwable = e))
                    }
                }


        disposable?.add(request)
    }

    fun fetchAllRocketLaunches() {
        statusLiveData.postValue(ScreenState.Loading)
        val request =
            rocketLaunchesApi.fetchAllRocketLaunches().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    statusLiveData.postValue(ScreenState.Success)
                    result?.let {
                        executor.execute {
                            rocketLaunchesDao.updateData(result.map {
                                mapper.toRocketLaunchDbEntity(
                                    it
                                )
                            })
                        }
                    }
                }, { e ->
                    if (e is UnknownHostException) {
                        statusLiveData.postValue(ScreenState.InternetError)
                    } else {
                        statusLiveData.postValue(ScreenState.Error(throwable = e))
                    }
                }
                )
        disposable?.add(request)
    }

    fun dispose() {
        statusLiveData.removeObserver { }
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }

}