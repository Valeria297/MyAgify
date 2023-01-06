package com.example.domain.usecases

import com.example.domain.entities.NameEntity
import com.example.domain.repository.AgePredictionRepository

class AddFavoriteNameUseCase(private val repository: AgePredictionRepository) {
    suspend operator fun invoke(entity: NameEntity) =
        repository.addFavoriteName(nameEntity = entity)
}