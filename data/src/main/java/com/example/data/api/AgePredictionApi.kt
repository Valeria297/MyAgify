package com.example.data.api

import com.example.data.entities.Person
import retrofit2.http.GET
import retrofit2.http.Query

interface AgePredictionApi {
    @GET("/")
    suspend fun getPersonInfo( @Query("name") name: String): Person
}