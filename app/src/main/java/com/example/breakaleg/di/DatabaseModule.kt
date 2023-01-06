package com.example.breakaleg.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.FavoriteNameDao
import com.example.data.db.FavoriteNameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideFavoriteNameDatabase(@ApplicationContext context: Context): FavoriteNameDatabase {
        return Room.databaseBuilder(context, FavoriteNameDatabase::class.java, "favoritename.db")
            .build()
    }

    @Provides
    fun provideFavoriteNameDao(favoriteNameDatabase: FavoriteNameDatabase): FavoriteNameDao {
        return favoriteNameDatabase.favoriteNameDao()
    }

}