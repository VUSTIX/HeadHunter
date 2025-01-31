package com.example.data.repository.remote

import com.example.data.mapper.toDomain
import com.example.data.model.remote.MainDataDto
import com.example.data.model.remote.VacancyDto
import com.example.domain.model.remote.MainData
import com.example.domain.model.remote.Vacancy
import com.example.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {

    override suspend fun getOffersAndVacancies(): MainData {
        val mainDataDto: MainDataDto = apiService.getOffersAndVacancies()
        return mainDataDto.toDomain()
    }

    override suspend fun getVacancyById(id: String): Vacancy? {
        val responseData: MainDataDto = apiService.getOffersAndVacancies()
        val vacancies: List<VacancyDto>? = responseData.vacancies
        return vacancies?.find { it.id == id }?.toDomain()
    }

    override suspend fun getFavoriteVacancies(): List<Vacancy>? {
        val responseData: MainDataDto = apiService.getOffersAndVacancies()
        val allVacancies: List<VacancyDto>? = responseData.vacancies
        return allVacancies?.filter { it.isFavorite }?.map { it.toDomain() }
    }
}