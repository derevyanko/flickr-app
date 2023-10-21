package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.converters.TagTypeConvertor
import com.example.data.local.dao.PhotosDao
import com.example.data.local.entities.PhotoDetailsDbEntity
import com.example.data.local.entities.RecentPhotoDbEntity

@Database(
    version = 1,
    entities = [
        RecentPhotoDbEntity::class,
        PhotoDetailsDbEntity::class
    ]
)
@TypeConverters(TagTypeConvertor::class)
abstract class PhotoDatabase: RoomDatabase() {

    abstract fun getPhotosDao(): PhotosDao
}