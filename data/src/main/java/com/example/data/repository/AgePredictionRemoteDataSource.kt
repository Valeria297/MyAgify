package com.example.data.repository

import com.example.data.api.AgePredictionApi
import com.example.domain.common.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AgePredictionRemoteDataSource(
    private val api: AgePredictionApi
) : AgePredictionDataSource.Remote {
    override suspend fun getAgeByName(name: String): ResultData<Int> = withContext(Dispatchers.IO) {
        return@withContext try {
            val person = api.getPersonInfo(name = name)
            ResultData.Success(data = person.age)
        } catch (e: Exception) {
            ResultData.Error(error = e)
        }
    }
}