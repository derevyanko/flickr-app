package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entities.PhotoDetailsDbEntity
import com.example.data.local.entities.RecentPhotoDbEntity

@Dao
interface PhotosDao {

    @Query("SELECT * FROM recent_photos WHERE date = :date")
    fun getRecentPhotos(date: String): List<RecentPhotoDbEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateRecentPhotos(photos: List<RecentPhotoDbEntity>)

    @Query("SELECT * FROM photo_details WHERE id = :photoId")
    fun getPhotoDetails(photoId: String): PhotoDetailsDbEntity?

    @Insert(entity = PhotoDetailsDbEntity::class, onConflict = OnConflictStrategy.IGNORE)
    fun updatePhotoDetails(photoDetailsDbEntity: PhotoDetailsDbEntity)
}