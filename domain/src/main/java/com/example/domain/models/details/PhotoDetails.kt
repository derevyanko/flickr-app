package com.example.domain.models.details

data class PhotoDetails(
    val id: String,
    val title: String,
    val description: String,
    val ownerUserName: String,
    val comments: String,
    val datePosted: String,
    val tags: List<Tag>,
    val url: String
)