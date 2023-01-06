package com.example.breakaleg.di

import com.example.domain.repository.AgePredictionRepository
import com.example.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetAgeByNameUseCase(repository: AgePredictionRepository): GetAgeByNameUseCase {
        return GetAgeByNameUseCase(repository = repository)
    }

    @Provides
    fun provideAddFavoriteNameUseCase(repository: AgePredictionRepository): AddFavoriteNameUseCase {
        return AddFavoriteNameUseCase(repository = repository)
    }

    @Provides
    fun provideGetFavoriteNamesUseCase(repository: AgePredictionRepository): GetFavoriteNamesUseCase {
        return GetFavoriteNamesUseCase(repository = repository)
    }

    @Provides
    fun provideGetFavoriteNameByIdUseCase(repository: AgePredictionRepository): GetFavoriteNameByIdUseCase {
        return GetFavoriteNameByIdUseCase(repository = repository)
    }

    @Provides
    fun provideRemoveFavoriteNameUseCase(repository: AgePredictionRepository): RemoveFavoriteNameUseCase {
        return RemoveFavoriteNameUseCase(repository = repository)
    }

}