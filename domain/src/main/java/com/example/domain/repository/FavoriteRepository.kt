package com.example.domain.repository

import com.example.domain.model.local.Favorite
import kotlinx.coroutines.flow.Flow


interface FavoriteRepository {

    fun getAllFavoritesVacancies(): Flow<List<Favorite>>

    suspend fun getVacancyById(vacancyId: String): Favorite?

    suspend fun isFavoritesVacancy(vacancyId: String): Boolean

    suspend fun insertFavoritesVacancy(vacancy: Favorite)

    suspend fun removeFavoriteVacancy(vacancy: Favorite)
}