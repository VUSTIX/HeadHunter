package com.example.domain.usecase.remote

import com.example.domain.model.remote.Vacancy
import com.example.domain.repository.ApiRepository

class GetVacancyByIdUseCase(
    private val repository: ApiRepository
) {
    suspend operator fun invoke(id: String): Vacancy? {
        return repository.getVacancyById(id)
    }
}