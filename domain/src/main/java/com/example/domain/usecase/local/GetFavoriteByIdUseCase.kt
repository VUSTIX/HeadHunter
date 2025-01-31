package com.example.domain.usecase.local

import com.example.domain.model.local.Favorite
import com.example.domain.repository.FavoriteRepository

class GetFavoriteByIdUseCase(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(vacancyId: String): Favorite? {
        return repository.getVacancyById(vacancyId)
    }
}