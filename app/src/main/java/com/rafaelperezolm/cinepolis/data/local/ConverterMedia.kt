package com.rafaelperezolm.cinepolis.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rafaelperezolm.cinepolis.data.entities.Media
import java.io.Serializable
import java.util.*


class ConverterMedia: Serializable {

    //Converter to get the Media values to String
    @TypeConverter
    fun fromOptionValuesList(optionValues: List<Media>): String {
        if (optionValues == null) {
            return ""
        }
        val gson = Gson()
        val type = object : TypeToken<List<Media?>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    //Converter to get the String values of Media to Media class
    @TypeConverter
    fun toOptionValuesList(optionValuesString: String): List<Media> {
        if (optionValuesString == null) {
            return Collections.emptyList();
        }
        val gson = Gson()
        val type = object : TypeToken<List<Media>>() {}.type
        return gson.fromJson<List<Media>>(optionValuesString, type)
    }

}

