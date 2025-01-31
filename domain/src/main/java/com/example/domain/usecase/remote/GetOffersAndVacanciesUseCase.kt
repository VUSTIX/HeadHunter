package com.example.domain.usecase.remote

import com.example.domain.model.remote.MainData
import com.example.domain.repository.ApiRepository

class GetOffersAndVacanciesUseCase(
    private val repository: ApiRepository
) {
    suspend operator fun invoke(): MainData {
        return repository.getOffersAndVacancies()
    }
}