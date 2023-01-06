package com.example.data.repository

import com.example.domain.common.ResultData
import com.example.domain.entities.NameEntity
import com.example.domain.repository.AgePredictionRepository

class AgePredictionRepositoryImpl(
    private val remote: AgePredictionDataSource.Remote,
    private val local: AgePredictionDataSource.Local
) : AgePredictionRepository {
    override suspend fun getAgeByName(name: String): ResultData<Int> = remote.getAgeByName(name)

    override suspend fun getFavoriteName(id: Int): ResultData<NameEntity> = local.getName(id)

    override suspend fun getFavoriteNames(): ResultData<List<NameEntity>> = local.getNames()

    override suspend fun addFavoriteName(nameEntity: NameEntity) = local.addName(nameEntity)

    override suspend fun removeFavoriteName(nameEntity: NameEntity) = local.removeName(nameEntity)
}