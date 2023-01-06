package com.example.data.mappers

import com.example.data.entities.FavoriteNameDbData
import com.example.domain.entities.NameEntity

fun FavoriteNameDbData.toDomain(): NameEntity {
    return NameEntity(id = id, name = name)
}

fun NameEntity.toData(): FavoriteNameDbData {
    return FavoriteNameDbData(id = id, name = name)
}