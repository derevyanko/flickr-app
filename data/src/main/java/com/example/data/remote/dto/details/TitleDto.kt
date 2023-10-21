package com.example.data.remote.dto.details

import com.google.gson.annotations.SerializedName

data class TitleDto(
    @SerializedName("_content")
    val title: String
)
