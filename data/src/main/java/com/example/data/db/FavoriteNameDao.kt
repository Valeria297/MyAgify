package com.example.data.db

import androidx.room.*
import com.example.data.entities.FavoriteNameDbData


@Dao
interface FavoriteNameDao {
    @Query("SELECT * FROM names")
    fun getAll(): List<FavoriteNameDbData>

    @Query("SELECT * FROM names where id=:nameId")
    fun get(nameId: Int): FavoriteNameDbData?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(nameDbData: FavoriteNameDbData)

    @Delete
    fun remove(nameDbData: FavoriteNameDbData)
}

