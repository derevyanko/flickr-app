package com.example.domain.repository

import com.example.domain.base.State
import com.example.domain.models.details.PhotoDetails
import com.example.domain.models.recent.RecentPhotos
import kotlinx.coroutines.flow.Flow

interface FlickrRepository {

    suspend fun getRecentPhotos(itemsPerPage: Int): Flow<State<RecentPhotos>>

    suspend fun getPhotoById(photoId: String): Flow<State<PhotoDetails>>
}