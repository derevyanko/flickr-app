package com.example.data.remote.dto.details

import com.google.gson.annotations.SerializedName

data class CommentsDto(
    @SerializedName("_content")
    val comments: String
)
