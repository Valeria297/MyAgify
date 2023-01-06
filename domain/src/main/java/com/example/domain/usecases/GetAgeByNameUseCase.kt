package com.example.domain.usecases

import com.example.domain.common.ResultData
import com.example.domain.repository.AgePredictionRepository

class GetAgeByNameUseCase(private val repository: AgePredictionRepository) {
    suspend operator fun invoke(name: String): ResultData<Int> =
        repository.getAgeByName(name = name)
}