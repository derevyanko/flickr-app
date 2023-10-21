package com.example.data.remote.dto.details

import com.google.gson.annotations.SerializedName

data class DescriptionDto(
    @SerializedName("_content")
    val description: String
)
