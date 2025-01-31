package com.example.domain.usecase.local

import com.example.domain.repository.FavoriteRepository

class IsFavoriteVacancyUseCase(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(vacancyId: String): Boolean {
        return repository.isFavoritesVacancy(vacancyId)
    }
}