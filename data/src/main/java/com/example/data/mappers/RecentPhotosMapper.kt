package com.example.data.mappers

import com.example.data.local.entities.RecentPhotoDbEntity
import com.example.data.remote.dto.recent.PhotoResponseDto
import com.example.data.remote.dto.recent.PhotosDataDto
import com.example.domain.models.recent.Photo
import com.example.domain.models.recent.RecentPhotos

object RecentPhotosMapper {

    fun fromDtoToDomain(from: PhotosDataDto) = RecentPhotos(
        photos = from.photo.map { fromDtoToDomain(it) }
    )

    private fun fromDtoToDomain(from: PhotoResponseDto) = Photo(
        id = from.id,
        title = from.title.takeIf { it.isNotBlank() } ?: "No title",
        url = "https://live.staticflickr.com/${from.server}/${from.id}_${from.secret}.jpg"
    )


    fun fromDtoToDbEntity(
        from: PhotosDataDto,
        currentDate: String
    ) = from.photo.map {
        fromDtoToDbEntity(
            from = it,
            date = currentDate
        )
    }

    private fun fromDtoToDbEntity(
        from: PhotoResponseDto,
        date: String
    ) = RecentPhotoDbEntity(
        id = from.id,
        title = from.title.takeIf { it.isNotBlank() } ?: "No title",
        url = "https://live.staticflickr.com/${from.server}/${from.id}_${from.secret}.jpg",
        date = date
    )


    fun fromDbEntityToDomain(from: List<RecentPhotoDbEntity>) = RecentPhotos(
        photos = from.map { fromDbEntityToDomain(it) }
    )

    private fun fromDbEntityToDomain(from: RecentPhotoDbEntity) = Photo(
        id = from.id,
        title = from.title.takeIf { it.isNotBlank() } ?: "No title",
        url = from.url
    )
}