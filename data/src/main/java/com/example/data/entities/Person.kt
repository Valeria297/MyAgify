package com.example.data.entities


import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("age")
    val age: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("name")
    val name: String
)