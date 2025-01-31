package com.example.data.repository.remote

import com.example.data.model.remote.MainDataDto
import retrofit2.http.GET

interface ApiService {
    @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getOffersAndVacancies(): MainDataDto
}