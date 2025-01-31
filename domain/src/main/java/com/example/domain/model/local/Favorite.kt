package com.example.domain.model.local

data class Favorite(
    val vacancyId: String,
    val title: String,
    val town: String,
    val company: String,
    val experience: String,
    val publishedDate: String,
    val createdAt: Long = System.currentTimeMillis()
)