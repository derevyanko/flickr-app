package com.example.data.remote.api

import com.example.data.remote.dto.details.PhotoInfoDto
import com.example.data.remote.dto.recent.PhotosSearchResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {

    @GET("services/rest/?method=flickr.interestingness.getList")
    suspend fun getList(@Query("per_page") itemsPerPage: Int): Response<PhotosSearchResponseDto>

    @GET("services/rest/?method=flickr.photos.getInfo")
    suspend fun getInfoById(@Query("photo_id") photoId: String): Response<PhotoInfoDto>
}