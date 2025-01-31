package com.example.data.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.local.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites_vacancies ORDER BY createdAt DESC")
    fun getAllFavoritesVacancies(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorites_vacancies WHERE vacancyId = :vacancyId LIMIT 1")
    suspend fun getVacancyById(vacancyId: String): FavoriteEntity

    @Query("SELECT EXISTS(SELECT 1 FROM favorites_vacancies WHERE vacancyId = :vacancyId)")
    suspend fun isFavoritesVacancy(vacancyId: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritesVacancy(favoritesVacancy: FavoriteEntity)

    @Delete
    suspend fun removeFavoritesVacancy(favoritesVacancy: FavoriteEntity)
}