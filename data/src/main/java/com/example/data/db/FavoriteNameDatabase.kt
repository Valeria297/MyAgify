package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.FavoriteNameDbData

@Database(
    entities = [FavoriteNameDbData::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteNameDatabase : RoomDatabase() {
    abstract fun favoriteNameDao(): FavoriteNameDao
}