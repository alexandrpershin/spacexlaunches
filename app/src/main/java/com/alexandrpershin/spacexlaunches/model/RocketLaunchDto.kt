package com.alexandrpershin.spacexlaunches.model

import com.google.gson.annotations.SerializedName

data class RocketLaunchDto(

    @SerializedName("crew")
    var crew: List<Any>,

    @SerializedName("details")
    var details: String,

    @SerializedName("flight_number")
    var flightNumber: Long,

    @SerializedName("is_tentative")
    var is_tentative: Boolean,

    @SerializedName("last_date_update")
    var last_date_update: String,

    @SerializedName("last_ll_launch_date")
    var last_ll_launch_date: String,

    @SerializedName("last_ll_update")
    var last_ll_update: String,

    @SerializedName("last_wiki_launch_date")
    var last_wiki_launch_date: String,

    @SerializedName("last_wiki_revision")
    var last_wiki_revision: String,

    @SerializedName("last_wiki_update")
    var last_wiki_update: String,

    @SerializedName("launch_date_local")
    var launch_date_local: String,

    @SerializedName("launch_date_source")
    var launch_date_source: String,

    @SerializedName("launch_date_unix")
    var launchDateUnix: Long,

    @SerializedName("launch_date_utc")
    var launch_date_utc: String,

    @SerializedName("launch_failure_details")
    var launch_failure_details: LaunchFailureDetails,

    @SerializedName("launch_site")
    var launch_site: LaunchSite,

    @SerializedName("launch_success")
    var launchSuccess: Boolean,

    @SerializedName("launch_window")
    var launch_window: Int,

    @SerializedName("launch_year")
    var launch_year: String,

    @SerializedName("links")
    var links: Links,

    @SerializedName("mission_id")
    var missionIds: List<String>? = emptyList(),

    @SerializedName("mission_name")
    var missionName: String,

    @SerializedName("rocket")
    var rocket: Rocket,

    @SerializedName("ships")
    var ships: List<Any>,

    @SerializedName("static_fire_date_unix")
    var static_fire_date_unix: Int,

    @SerializedName("static_fire_date_utc")
    var static_fire_date_utc: String,

    @SerializedName("tbd")
    var tbd: Boolean,

    @SerializedName("telemetry")
    var telemetry: Telemetry,

    @SerializedName("tentative_max_precision")
    var tentative_max_precision: String,

    @SerializedName("timeline")
    var timeline: Timeline,

    @SerializedName("upcoming")
    var upcoming: Boolean
)

data class LaunchFailureDetails(

    @SerializedName("altitude")
    var altitude: Int,

    @SerializedName("reason")
    var reason: String,

    @SerializedName("time")
    var time: Int
)

data class LaunchSite(

    @SerializedName("site_id")
    var site_id: String,

    @SerializedName("site_name")
    var site_name: String,

    @SerializedName("site_name_long")
    var site_name_long: String
)

data class Links(
    @SerializedName("article_link")
    var article_link: Any,

    @SerializedName("flickr_images")
    var flickr_images: List<Any>,

    @SerializedName("mission_patch")
    var missionPatch: String,

    @SerializedName("mission_patch_small")
    var mission_patch_small: Any,

    @SerializedName("presskit")
    var presskit: Any,

    @SerializedName("reddit_campaign")
    var reddit_campaign: Any,

    @SerializedName("reddit_launch")
    var reddit_launch: Any,

    @SerializedName("reddit_media")
    var reddit_media: Any,

    @SerializedName("reddit_recovery")
    var reddit_recovery: Any,

    @SerializedName("video_link")
    var video_link: Any,

    @SerializedName("wikipedia")
    var wikipedia: Any,

    @SerializedName("youtube_id")
    var youtube_id: Any
)

data class Rocket(
    @SerializedName("fairings")
    var fairings: Fairings,

    @SerializedName("first_stage")
    var firstStage: FirstStage,

    @SerializedName("rocket_id")
    var rocketId: String,

    @SerializedName("rocket_name")
    var rocketName: String,

    @SerializedName("rocket_type")
    var rocketType: String,

    @SerializedName("second_stage")
    var secondStage: SecondStage
)

data class Fairings(
    @SerializedName("recovered")
    var recovered: Boolean,

    @SerializedName("recovery_attempt")
    var recovery_attempt: Boolean,

    @SerializedName("reused")
    var reused: Boolean,

    @SerializedName("ship")
    var ship: Any
)

data class FirstStage(
    @SerializedName("cores")
    var cores: List<Core>
)

data class Core(

    @SerializedName("block")
    var block: Int,

    @SerializedName("core_serial")
    var core_serial: Any,

    @SerializedName("flight")
    var flight: Any,

    @SerializedName("gridfins")
    var gridfins: Any,

    @SerializedName("land_success")
    var land_success: Any,

    @SerializedName("landing_intent")
    var landing_intent: Any,

    @SerializedName("landing_type")
    var landing_type: Any,

    @SerializedName("landing_vehicle")
    var landing_vehicle: Any,

    @SerializedName("legs")
    var legs: Any,

    @SerializedName("reused")
    var reused: Boolean
)

data class SecondStage(
    @SerializedName("block")
    var block: Int,

    @SerializedName("payloads")
    var payloads: List<Payload>
)

data class Payload(
    @SerializedName("customers")
    var customers: List<String>,

    @SerializedName("manufacturer")
    var manufacturer: String,

    @SerializedName("nationality")
    var nationality: String,

    @SerializedName("norad_id")
    var norad_id: List<Any>,

    @SerializedName("orbit")
    var orbit: String,

    @SerializedName("orbit_params")
    var orbit_params: OrbitParams,

    @SerializedName("payload_id")
    var payload_id: String,

    @SerializedName("payload_mass_kg")
    var payload_mass_kg: Any,

    @SerializedName("payload_mass_lbs")
    var payload_mass_lbs: Any,

    @SerializedName("payload_type")
    var payload_type: String,

    @SerializedName("reused")
    var reused: Boolean,

    @SerializedName("uid")
    var uid: String
)

data class OrbitParams(
    @SerializedName("apoapsis_km")
    var apoapsis_km: Any,

    @SerializedName("arg_of_pericenter")
    var arg_of_pericenter: Any,

    @SerializedName("eccentricity")
    var eccentricity: Any,

    @SerializedName("epoch")
    var epoch: Any,

    @SerializedName("inclination_deg")
    var inclination_deg: Any,

    @SerializedName("lifespan_years")
    var lifespan_years: Any,

    @SerializedName("longitude")
    var longitude: Any,

    @SerializedName("mean_anomaly")
    var mean_anomaly: Any,

    @SerializedName("mean_motion")
    var mean_motion: Any,

    @SerializedName("periapsis_km")
    var periapsis_km: Any,

    @SerializedName("period_min")
    var period_min: Any,

    @SerializedName("raan")
    var raan: Any,

    @SerializedName("reference_system")
    var reference_system: String,

    @SerializedName("regime")
    var regime: String,

    @SerializedName("semi_major_axis_km")
    var semi_major_axis_km: Any
)

data class Telemetry(
    @SerializedName("flight_club")
    var flight_club: Any
)

data class Timeline(
    @SerializedName("beco")
    var beco: Int,

    @SerializedName("center_core_entry_burn")
    var center_core_entry_burn: Int,

    @SerializedName("center_core_landing")
    var center_core_landing: Int,

    @SerializedName("center_stage_sep")
    var center_stage_sep: Int,

    @SerializedName("engine_chill")
    var engine_chill: Int,

    @SerializedName("fairing_deploy")
    var fairing_deploy: Int,

    @SerializedName("go_for_launch")
    var go_for_launch: Int,

    @SerializedName("go_for_prop_loading")
    var go_for_prop_loading: Int,

    @SerializedName("ignition")
    var ignition: Int,

    @SerializedName("liftoff")
    var liftoff: Int,

    @SerializedName("maxq")
    var maxq: Int,

    @SerializedName("meco")
    var meco: Int,

    @SerializedName("payload_deploy")
    var payload_deploy: Int,

    @SerializedName("prelaunch_checks")
    var prelaunch_checks: Int,

    @SerializedName("propellant_pressurization")
    var propellant_pressurization: Int,

    @SerializedName("seco-1")
    var seco_1: Int,

    @SerializedName("seco-2")
    var seco_2: Int,

    @SerializedName("seco-3")
    var seco_3: Int,

    @SerializedName("seco-4")
    var seco_4: Int,

    @SerializedName("second_stage_ignition")
    var second_stage_ignition: Int,

    @SerializedName("second_stage_restart")
    var second_stage_restart: Int,

    @SerializedName("side_core_boostback")
    var side_core_boostback: Int,

    @SerializedName("side_core_entry_burn")
    var side_core_entry_burn: Int,

    @SerializedName("side_core_landing")
    var side_core_landing: Int,

    @SerializedName("side_core_sep")
    var side_core_sep: Int,

    @SerializedName("stage1_lox_loading")
    var stage1_lox_loading: Int,

    @SerializedName("stage1_rp1_loading")
    var stage1_rp1_loading: Int,

    @SerializedName("stage2_lox_loading")
    var stage2_lox_loading: Int,

    @SerializedName("stage2_rp1_loading")
    var stage2_rp1_loading: Int,

    @SerializedName("webcast_liftoff")
    var webcast_liftoff: Any
)