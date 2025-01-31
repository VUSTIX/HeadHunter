package com.example.data.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.local.FavoriteEntity
import com.example.data.repository.local.FavoriteDao

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesVacancyDao(): FavoriteDao
}