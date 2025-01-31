package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.remote.Offer
import com.example.domain.model.remote.Vacancy
import com.example.domain.usecase.remote.GetOffersAndVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOffersAndVacanciesUseCase: GetOffersAndVacanciesUseCase
) : ViewModel() {

    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers: StateFlow<List<Offer>> = _offers

    private val _vacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancies: StateFlow<List<Vacancy>> = _vacancies

    private val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    init {
        fetchOffersAndVacancies()
    }

    private fun fetchOffersAndVacancies() {
        if (!_isDataLoaded.value) {
            viewModelScope.launch {
                try {
                    val responseData = getOffersAndVacanciesUseCase()
                    _offers.value = responseData.offers ?: emptyList()
                    _vacancies.value = responseData.vacancies ?: emptyList()
                    _isDataLoaded.value = true
                } catch (e: Exception) {
                    /* TODO */
                }
            }
        }
    }

}