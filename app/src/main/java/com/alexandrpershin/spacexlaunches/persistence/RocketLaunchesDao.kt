package com.alexandrpershin.spacexlaunches.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alexandrpershin.spacexlaunches.model.Rocket
import com.alexandrpershin.spacexlaunches.model.RocketLaunchDbEntity

@Dao
interface RocketLaunchesDao {

    @Transaction
    fun updateData(launches: List<RocketLaunchDbEntity>) {
        deleteLaunches()
        insertLaunches(launches)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLaunches(launches: List<RocketLaunchDbEntity>)

    @Query("SELECT * from RocketLaunch")
    fun getAllLaunches(): LiveData<List<RocketLaunchDbEntity>>

    @Query("SELECT  * from RocketLaunch where id=:launchId")
    fun getRocketLaunchById(launchId: Long): LiveData<RocketLaunchDbEntity>

    @Query("DELETE FROM RocketLaunch")
    fun deleteLaunches()
}