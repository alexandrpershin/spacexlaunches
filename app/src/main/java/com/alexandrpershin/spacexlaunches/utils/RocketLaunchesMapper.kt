package com.alexandrpershin.spacexlaunches.utils

import com.alexandrpershin.spacexlaunches.model.RocketLaunchDbEntity
import com.alexandrpershin.spacexlaunches.model.RocketLaunchDto

class RocketLaunchesMapper {

    fun toRocketLaunchDbEntity(dto: RocketLaunchDto): RocketLaunchDbEntity {
        return RocketLaunchDbEntity(
            id = dto.flightNumber,
            missionName = dto.missionName,
            launchDateUnix = dto.launchDateUnix,
            missionId = dto.missionIds?.joinToString(separator = ","),
            details = dto.details,
            imageUrl = dto.links.missionPatch,
            rocket = dto.rocket,
            launchSuccess = dto.launchSuccess
        )

    }
}