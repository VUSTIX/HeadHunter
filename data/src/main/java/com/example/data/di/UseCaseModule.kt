package com.example.data.di

import com.example.domain.repository.ApiRepository
import com.example.domain.repository.FavoriteRepository
import com.example.domain.usecase.local.GetAllFavoritesVacanciesUseCase
import com.example.domain.usecase.local.GetFavoriteByIdUseCase
import com.example.domain.usecase.local.InsertFavoriteVacancyUseCase
import com.example.domain.usecase.local.IsFavoriteVacancyUseCase
import com.example.domain.usecase.local.RemoveFavoriteVacancyUseCase
import com.example.domain.usecase.remote.GetFavoriteVacanciesUseCase
import com.example.domain.usecase.remote.GetOffersAndVacanciesUseCase
import com.example.domain.usecase.remote.GetVacancyByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllFavoritesVacanciesUseCase(
        favoriteRepository: FavoriteRepository
    ): GetAllFavoritesVacanciesUseCase = GetAllFavoritesVacanciesUseCase(favoriteRepository)

    @Provides
    fun provideGetFavoriteByIdUseCase(
        favoriteRepository: FavoriteRepository
    ): GetFavoriteByIdUseCase = GetFavoriteByIdUseCase(favoriteRepository)

    @Provides
    fun provideInsertFavoriteVacancyUseCase(
        favoriteRepository: FavoriteRepository
    ): InsertFavoriteVacancyUseCase = InsertFavoriteVacancyUseCase(favoriteRepository)

    @Provides
    fun provideIsFavoriteVacancyUseCase(
        favoriteRepository: FavoriteRepository
    ): IsFavoriteVacancyUseCase = IsFavoriteVacancyUseCase(favoriteRepository)

    @Provides
    fun provideRemoveFavoriteVacancyUseCase(
        favoriteRepository: FavoriteRepository
    ): RemoveFavoriteVacancyUseCase = RemoveFavoriteVacancyUseCase(favoriteRepository)

    @Provides
    fun provideGetFavoriteVacanciesUseCase(
        apiRepository: ApiRepository
    ): GetFavoriteVacanciesUseCase = GetFavoriteVacanciesUseCase(apiRepository)

    @Provides
    fun provideGetOffersAndVacanciesUseCase(
        apiRepository: ApiRepository
    ): GetOffersAndVacanciesUseCase = GetOffersAndVacanciesUseCase(apiRepository)

    @Provides
    fun provideGetVacancyByIdUseCase(
        apiRepository: ApiRepository
    ): GetVacancyByIdUseCase = GetVacancyByIdUseCase(apiRepository)
}