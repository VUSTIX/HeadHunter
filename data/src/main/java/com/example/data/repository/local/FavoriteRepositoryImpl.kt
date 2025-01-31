package com.example.data.repository.local

import com.example.data.mapper.toDomain
import com.example.data.mapper.toEntity
import com.example.data.model.local.FavoriteEntity
import com.example.domain.model.local.Favorite
import com.example.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {

    override fun getAllFavoritesVacancies(): Flow<List<Favorite>> {
        return favoriteDao.getAllFavoritesVacancies().map { entityList ->
            entityList.map { entity -> entity.toDomain() }
        }
    }

    override suspend fun getVacancyById(vacancyId: String): Favorite {
        val entity: FavoriteEntity = favoriteDao.getVacancyById(vacancyId)
        return entity.toDomain()
    }

    override suspend fun isFavoritesVacancy(vacancyId: String): Boolean {
        return favoriteDao.isFavoritesVacancy(vacancyId)
    }

    override suspend fun insertFavoritesVacancy(vacancy: Favorite) {
        favoriteDao.insertFavoritesVacancy(vacancy.toEntity())
    }

    override suspend fun removeFavoriteVacancy(vacancy: Favorite) {
        favoriteDao.removeFavoritesVacancy(vacancy.toEntity())
    }
}