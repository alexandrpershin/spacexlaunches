package com.alexandrpershin.spacexlaunches.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alexandrpershin.spacexlaunches.model.RocketLaunchDbEntity
import com.alexandrpershin.spacexlaunches.utils.Constants.DATABASE_NAME

@Database(
    entities = [RocketLaunchDbEntity::class],
    version = 3,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun rocketLaunchesDao(): RocketLaunchesDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                LocalDatabase::class.java, DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}