package com.example.data.remote.dto.details

import com.google.gson.annotations.SerializedName

data class TagDto (
    val id: String,
    @SerializedName("authorname")
    val authorName: String,
    val raw: String,
    @SerializedName("_content")
    val content: String,
)