package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.remote.Vacancy
import com.example.domain.usecase.remote.GetOffersAndVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getOffersAndVacanciesUseCase: GetOffersAndVacanciesUseCase
) : ViewModel() {

    private val _vacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancies: StateFlow<List<Vacancy>> = _vacancies

    private val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    init {
        fetchVacancies()
    }

    private fun fetchVacancies() {
        if (!_isDataLoaded.value) {
            viewModelScope.launch {
                try {
                    val responseData = getOffersAndVacanciesUseCase()
                    _vacancies.value = responseData.vacancies ?: emptyList()
                    _isDataLoaded.value = true
                } catch (e: Exception) {
                    /* TODO */
                }
            }
        }
    }

}