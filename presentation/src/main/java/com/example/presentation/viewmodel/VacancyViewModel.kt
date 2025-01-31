package com.example.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.remote.Vacancy
import com.example.domain.usecase.remote.GetVacancyByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacancyViewModel @Inject constructor(
    private val getVacancyByIdUseCase: GetVacancyByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val vacancyId: String = savedStateHandle["vacancyId"]
        ?: throw IllegalArgumentException("Данная вакансия не найдена")

    private val _vacancy = MutableStateFlow<Vacancy?>(null)
    val vacancy: StateFlow<Vacancy?> = _vacancy

    private val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    init {
        fetchVacancy()
    }

    private fun fetchVacancy() {
        if (!_isDataLoaded.value) {
            viewModelScope.launch {
                try {
                    val fetchedVacancy = getVacancyByIdUseCase(vacancyId)
                    _vacancy.value = fetchedVacancy
                    _isDataLoaded.value = true
                } catch (e: Exception) {
                    /* TODO */
                }
            }
        }
    }

}