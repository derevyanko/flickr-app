package com.example.data.remote.dataSource

import com.example.data.remote.api.FlickrApiService
import com.example.data.remote.base.BaseRepository

class FlickrRemoteDataSourceImpl(
    private val apiService: FlickrApiService
): BaseRepository(), FlickrRemoteDataSource {

    override suspend fun getRecentPhotos(itemsPerPage: Int) = request {
        apiService.getList(itemsPerPage)
    }

    override suspend fun getInfoById(photoId: String) = request {
        apiService.getInfoById(photoId)
    }
}