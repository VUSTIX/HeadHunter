package com.example.domain.usecase.local

import com.example.domain.model.local.Favorite
import com.example.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoritesVacanciesUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(): Flow<List<Favorite>> {
        return repository.getAllFavoritesVacancies()
    }
}