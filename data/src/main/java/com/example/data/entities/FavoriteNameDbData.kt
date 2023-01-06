package com.example.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "names", indices = [Index(value = ["name"], unique = true)])
data class FavoriteNameDbData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)