package com.example.data.repository

import com.example.domain.common.ResultData
import com.example.domain.entities.NameEntity

interface AgePredictionDataSource {

    interface Remote {
        suspend fun getAgeByName(name: String): ResultData<Int>
    }

    interface Local {
        suspend fun getNames(): ResultData<List<NameEntity>>
        suspend fun getName(nameId: Int): ResultData<NameEntity>
        suspend fun addName(entity: NameEntity)
        suspend fun removeName(entity: NameEntity)
    }
}