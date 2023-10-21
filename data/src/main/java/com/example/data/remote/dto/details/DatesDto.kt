package com.example.data.remote.dto.details

import com.google.gson.annotations.SerializedName

data class DatesDto (
    val posted: String,
    val taken: String,
    @SerializedName("lastupdate")
    val lastUpdate: String
)
