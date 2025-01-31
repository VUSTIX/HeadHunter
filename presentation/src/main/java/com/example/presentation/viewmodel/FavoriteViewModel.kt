package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.local.Favorite
import com.example.domain.model.remote.Vacancy
import com.example.domain.usecase.local.GetAllFavoritesVacanciesUseCase
import com.example.domain.usecase.local.GetFavoriteByIdUseCase
import com.example.domain.usecase.local.InsertFavoriteVacancyUseCase
import com.example.domain.usecase.local.IsFavoriteVacancyUseCase
import com.example.domain.usecase.local.RemoveFavoriteVacancyUseCase
import com.example.domain.usecase.remote.GetOffersAndVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getOffersAndVacanciesUseCase: GetOffersAndVacanciesUseCase,
    private val getAllFavoritesVacanciesUseCase: GetAllFavoritesVacanciesUseCase,
    private val getFavoriteByIdUseCase: GetFavoriteByIdUseCase,
    private val insertFavoriteVacancyUseCase: InsertFavoriteVacancyUseCase,
    private val isFavoriteVacancyUseCase: IsFavoriteVacancyUseCase,
    private val removeFavoriteVacancyUseCase: RemoveFavoriteVacancyUseCase
) : ViewModel() {

    private val _allFavoritesVacancies = MutableStateFlow<List<Favorite>>(emptyList())
    val allFavoritesVacancies: StateFlow<List<Favorite>> = _allFavoritesVacancies

    private val _vacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancies: StateFlow<List<Vacancy>> = _vacancies

    private val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    val favoriteVacancies: StateFlow<List<Vacancy>> = combine(
        _allFavoritesVacancies,
        _vacancies
    ) { favorites, vacancies ->
        vacancies.filter { vacancy ->
            favorites.any { favorite -> favorite.vacancyId == vacancy.id }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        fetchVacancies()
        viewModelScope.launch {
            getAllFavoritesVacanciesUseCase().collect { favorites ->
                _allFavoritesVacancies.value = favorites
            }
        }
    }

    private fun fetchVacancies() {
        if (!_isDataLoaded.value) {
            viewModelScope.launch {
                try {
                    val responseData = getOffersAndVacanciesUseCase()
                    _vacancies.value = responseData.vacancies ?: emptyList()
                    _isDataLoaded.value = true
                } catch (e: Exception) {
                    // Обработка ошибок
                }
            }
        }
    }

    fun addFavoriteVacancy(vacancy: Favorite) {
        viewModelScope.launch {
            insertFavoriteVacancyUseCase(vacancy)
        }
    }

    fun removeFavoriteVacancy(vacancy: Favorite) {
        viewModelScope.launch {
            removeFavoriteVacancyUseCase(vacancy)
        }
    }

    fun removeFavoriteVacancyById(vacancyId: String) {
        viewModelScope.launch {
            val vacancy = getFavoriteByIdUseCase(vacancyId)
            vacancy?.let {
                removeFavoriteVacancyUseCase(it)
            }
        }
    }

}