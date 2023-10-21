package com.example.data.remote.dto.details

import com.google.gson.annotations.SerializedName

data class OwnerDto(
    @SerializedName("nsid")
    val id: String,
    val username: String,
    @SerializedName("realname")
    val realName: String,
    val location: String
)
