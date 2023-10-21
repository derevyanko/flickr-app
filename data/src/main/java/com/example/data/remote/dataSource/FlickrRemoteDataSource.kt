package com.example.data.remote.dataSource

import com.example.data.remote.dto.details.PhotoInfoDto
import com.example.data.remote.dto.recent.PhotosSearchResponseDto
import com.example.data.remote.base.NetworkState

interface FlickrRemoteDataSource {

    suspend fun getRecentPhotos(itemsPerPage: Int): NetworkState<PhotosSearchResponseDto>

    suspend fun getInfoById(photoId: String): NetworkState<PhotoInfoDto>
}