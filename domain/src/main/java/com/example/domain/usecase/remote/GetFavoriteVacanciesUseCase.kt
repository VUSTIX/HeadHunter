package com.example.domain.usecase.remote

import com.example.domain.model.remote.Vacancy
import com.example.domain.repository.ApiRepository

class GetFavoriteVacanciesUseCase(
    private val repository: ApiRepository
) {
    suspend operator fun invoke(): List<Vacancy>? {
        return repository.getFavoriteVacancies()
    }
}