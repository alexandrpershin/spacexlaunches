package com.alexandrpershin.spacexlaunches.persistence

import androidx.room.TypeConverter
import com.alexandrpershin.spacexlaunches.model.Rocket
import com.google.gson.Gson


/**
 * This class converts from a unknown type into a known type in terms of database types
 * */
class Converters {

    private val gson = Gson()

    @TypeConverter
    fun getRocketString(json: String): Rocket? {
        return gson.fromJson<Rocket>(json, Rocket::class.java)
    }

    @TypeConverter
    fun getStringRocket(rocket: Rocket?): String {
        return gson.toJson(rocket)
    }

}
