package com.example.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_vacancies")
data class FavoriteEntity(
    @PrimaryKey val vacancyId: String,
    val title: String,
    val town: String,
    val company: String,
    val experience: String,
    val publishedDate: String,
    val createdAt: Long = System.currentTimeMillis()
)