package com.example.data.repository

import com.example.data.db.FavoriteNameDao
import com.example.data.mappers.toData
import com.example.data.mappers.toDomain
import com.example.domain.common.ResultData
import com.example.domain.entities.NameEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AgePredictionLocalDataSource(
    private val dao: FavoriteNameDao
): AgePredictionDataSource.Local {
    override suspend fun getNames(): ResultData<List<NameEntity>>  =
        withContext(Dispatchers.IO) {
            val names = dao.getAll()
            return@withContext if(names.isNotEmpty()) {
                ResultData.Success(names.map { it.toDomain() })
            } else {
                ResultData.Error(Exception())
            }
        }

    override suspend fun getName(nameId: Int): ResultData<NameEntity> =
        withContext(Dispatchers.IO) {
            return@withContext dao.get(nameId)?.let { ResultData.Success(it.toDomain()) } ?: ResultData.Error(Exception())
        }

    override suspend fun addName(entity: NameEntity) = withContext(Dispatchers.IO) {
        dao.add(entity.toData())
    }

    override suspend fun removeName(entity: NameEntity) = withContext(Dispatchers.IO) {
        dao.remove(entity.toData())
    }
}