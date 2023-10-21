package com.example.data.local.dataSource

import com.example.data.local.dao.PhotosDao
import com.example.data.local.entities.PhotoDetailsDbEntity
import com.example.data.local.entities.RecentPhotoDbEntity

class FlickrLocalDataSourceImpl(
    private val photosDao: PhotosDao
): FlickrLocalDataSource {

    override suspend fun getRecentPhotos(date: String) = photosDao.getRecentPhotos(date) ?: emptyList()

    override suspend fun updateRecentPhotos(photos: List<RecentPhotoDbEntity>) {
        photosDao.updateRecentPhotos(photos)
    }

    override suspend fun getPhotoDetails(photoId: String) = photosDao.getPhotoDetails(photoId)

    override suspend fun updatePhotoDetails(photoDetails: PhotoDetailsDbEntity) {
        photosDao.updatePhotoDetails(photoDetails)
    }
}