package com.example.data.mappers

import com.example.data.local.entities.PhotoDetailsDbEntity
import com.example.data.remote.dto.details.PhotoDetailsDto
import com.example.data.remote.dto.details.TagDto
import com.example.data.remote.dto.details.TagsDto
import com.example.domain.models.details.PhotoDetails
import com.example.domain.models.details.Tag

object PhotoDetailsMapper {

    fun fromDtoToDomain(from: PhotoDetailsDto) = PhotoDetails(
        id = from.id,
        title = from.title.title,
        description = from.description.description,
        ownerUserName = from.owner.username,
        comments = from.comments.comments,
        datePosted = from.dates.posted,
        tags = from.tags.toDomain(),
        url = "https://live.staticflickr.com/${from.server}/${from.id}_${from.secret}.jpg"
    )

    private fun TagsDto.toDomain() = this.tag.map { it.toDomainTag() }

    private fun TagDto.toDomainTag() = Tag(
        id = this.id,
        authorName = this.authorName,
        content = this.content
    )


    fun fromDtoToDbEntity(from: PhotoDetailsDto) = PhotoDetailsDbEntity(
        id = from.id,
        server = from.server,
        secret = from.secret,
        title = from.title.title,
        description = from.description.description,
        ownerUserName = from.owner.username,
        comments = from.comments.comments,
        datePosted = from.dates.posted,
        tags = from.tags.toDbEntity()
    )

    private fun TagsDto.toDbEntity() = this.tag.map {
        it.toDbEntity()
    }

    private fun TagDto.toDbEntity() = PhotoDetailsDbEntity.Tag(
        id = this.id,
        authorName = this.authorName,
        content = this.content
    )


    fun fromDbEntityToDomain(from: PhotoDetailsDbEntity) = PhotoDetails(
        id = from.id,
        title = from.title,
        description = from.description,
        ownerUserName = from.ownerUserName,
        comments = from.comments,
        datePosted = from.datePosted,
        tags = from.tags.toDomain(),
        url = "https://live.staticflickr.com/${from.server}/${from.id}_${from.secret}.jpg"
    )

    private fun List<PhotoDetailsDbEntity.Tag>.toDomain() = this.map {
        it.toDomain()
    }

    private fun PhotoDetailsDbEntity.Tag.toDomain() = Tag(
        id = this.id,
        authorName = this.authorName,
        content = this.content
    )
}