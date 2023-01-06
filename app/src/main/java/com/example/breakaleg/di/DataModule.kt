package com.example.breakaleg.di

import com.example.data.api.AgePredictionApi
import com.example.data.db.FavoriteNameDao
import com.example.data.repository.AgePredictionDataSource
import com.example.data.repository.AgePredictionLocalDataSource
import com.example.data.repository.AgePredictionRemoteDataSource
import com.example.data.repository.AgePredictionRepositoryImpl
import com.example.domain.repository.AgePredictionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideAgePredictionRepository(remote: AgePredictionDataSource.Remote, local:  AgePredictionDataSource.Local): AgePredictionRepository {
        return AgePredictionRepositoryImpl(remote = remote, local = local)
    }

    @Provides
    fun provideAgePredictionRemoteDataSource(api: AgePredictionApi): AgePredictionDataSource.Remote {
        return AgePredictionRemoteDataSource(api = api)
    }

    @Provides
    fun provideAgePredictionLocalDataSource(dao: FavoriteNameDao): AgePredictionDataSource.Local {
        return AgePredictionLocalDataSource(dao = dao)
    }
}