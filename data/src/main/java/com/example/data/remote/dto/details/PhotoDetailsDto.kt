package com.example.data.remote.dto.details

data class PhotoDetailsDto(
    val id: String,
    val server: String,
    val secret: String,
    val owner: OwnerDto,
    val title: TitleDto,
    val description: DescriptionDto,
    val dates: DatesDto,
    val comments: CommentsDto,
    val tags: TagsDto
)