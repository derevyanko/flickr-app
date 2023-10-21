package com.example.data.local.dataSource

import com.example.data.local.entities.PhotoDetailsDbEntity
import com.example.data.local.entities.RecentPhotoDbEntity

interface FlickrLocalDataSource {

    suspend fun getRecentPhotos(date: String): List<RecentPhotoDbEntity>

    suspend fun updateRecentPhotos(photos: List<RecentPhotoDbEntity>)

    suspend fun getPhotoDetails(photoId: String): PhotoDetailsDbEntity?

    suspend fun updatePhotoDetails(photoDetails: PhotoDetailsDbEntity)
}