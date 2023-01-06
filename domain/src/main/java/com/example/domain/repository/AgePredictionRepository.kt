package com.example.domain.repository

import com.example.domain.common.ResultData
import com.example.domain.entities.NameEntity

interface AgePredictionRepository {
    suspend fun getAgeByName(name: String): ResultData<Int>
    suspend fun getFavoriteName(id: Int): ResultData<NameEntity>
    suspend fun getFavoriteNames(): ResultData<List<NameEntity>>
    suspend fun addFavoriteName(nameEntity: NameEntity)
    suspend fun removeFavoriteName(nameEntity: NameEntity)
}