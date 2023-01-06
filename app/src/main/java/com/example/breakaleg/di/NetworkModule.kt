package com.example.breakaleg.di

import com.example.data.api.AgePredictionApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.agify.io")
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): AgePredictionApi {
        return retrofit.create(AgePredictionApi::class.java)
    }
}