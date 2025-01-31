package com.example.domain.usecase.local

import com.example.domain.model.local.Favorite
import com.example.domain.repository.FavoriteRepository

class RemoveFavoriteVacancyUseCase(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(favorite: Favorite) {
        repository.removeFavoriteVacancy(favorite)
    }
}