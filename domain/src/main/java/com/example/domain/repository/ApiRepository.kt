package com.example.domain.repository

import com.example.domain.model.remote.MainData
import com.example.domain.model.remote.Vacancy

interface ApiRepository {

    suspend fun getOffersAndVacancies(): MainData

    suspend fun getVacancyById(id: String): Vacancy?

    suspend fun getFavoriteVacancies(): List<Vacancy>?
}