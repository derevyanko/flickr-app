package com.example.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "photo_details",
    indices = [
        Index("id", unique = true)
    ]
)
data class PhotoDetailsDbEntity(
    @PrimaryKey val id: String,
    val server: String,
    val secret: String,
    @ColumnInfo(name = "owner_user_name") val ownerUserName: String,
    val title: String,
    val description: String,
    val datePosted: String,
    val comments: String,
    val tags: List<Tag>
) {

    data class Tag(
        val id: String,
        val authorName: String,
        val content: String
    )
}