package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_photos")
data class RecentPhotoDbEntity(
    @PrimaryKey val id: String,
    val title: String,
    val url: String,
    val date: String
)
