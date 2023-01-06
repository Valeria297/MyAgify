package com.example.domain.usecases

import com.example.domain.common.ResultData
import com.example.domain.entities.NameEntity
import com.example.domain.repository.AgePredictionRepository

class GetFavoriteNameByIdUseCase(private val repository: AgePredictionRepository) {
    suspend operator fun invoke(id: Int): ResultData<NameEntity> =
        repository.getFavoriteName(id = id)
}