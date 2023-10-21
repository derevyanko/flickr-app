package com.example.data.remote.dto.recent

data class PhotoResponseDto(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String
)