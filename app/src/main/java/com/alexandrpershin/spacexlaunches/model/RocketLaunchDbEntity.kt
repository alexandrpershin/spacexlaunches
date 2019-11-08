package com.alexandrpershin.spacexlaunches.model

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexandrpershin.spacexlaunches.R
import com.alexandrpershin.spacexlaunches.utils.Constants.ROCKET_LAUNCH_TABLE
import kotlin.random.Random

@Entity(tableName = ROCKET_LAUNCH_TABLE)
class RocketLaunchDbEntity(

    @PrimaryKey(autoGenerate = false) var id: Long = 0,
    var missionName: String? = "",
    var launchSuccess: Boolean? = false,
    var launchDateUnix: Long,
    var missionId: String? = "",
    var details: String? = "",
    var imageUrl: String? = "",
    var rocket: Rocket? = null
)

object RocketLaunchUtils {
    fun getDetails(context: Context, details: String?): CharSequence? {
        return if (details.isNullOrEmpty()) context.getString(R.string.message_no_details) else details
    }

    fun getStatus(context: Context, launchSuccess: Boolean?): CharSequence? {
        return when (launchSuccess) {
            true -> {
                context.getString(R.string.status_message_success)
            }
            else -> {
                context.getString(R.string.status_message_failure)
            }
        }
    }

    fun getMissionId(missionId: String?): String? {
        return if (missionId.isNullOrEmpty()) {
            "MIS${Random.nextInt(1001, 9999)}"
        } else {
            missionId
        }
    }
}

