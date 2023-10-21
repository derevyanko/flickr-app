package com.example.data.local.converters

import androidx.room.TypeConverter
import com.example.data.local.entities.PhotoDetailsDbEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TagTypeConvertor {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun tagsToString(tags: List<PhotoDetailsDbEntity.Tag>): String {
        return gson.toJson(tags)
    }

    @TypeConverter
    @JvmStatic
    fun stringToTags(data: String): List<PhotoDetailsDbEntity.Tag> {
        val listType = object : TypeToken<List<PhotoDetailsDbEntity.Tag>>() {}.type
        return gson.fromJson(data, listType)
    }
}