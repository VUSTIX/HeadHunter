package com.example.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.ui.theme.title1
import com.example.presentation.R
import com.example.presentation.ui.components.ButtonBlue
import com.example.presentation.ui.components.OfferItem
import com.example.presentation.ui.components.RespondBottomSheet
import com.example.presentation.ui.components.SearchTopBar
import com.example.presentation.ui.components.VacancyItem
import com.example.presentation.ui.placeholders.OfferItemPlaceHolder
import com.example.presentation.ui.placeholders.VacancyItemPlaceHolder
import com.example.presentation.viewmodel.HomeViewModel
import com.example.presentation.utils.getDeclinedWord

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val offers by homeViewModel.offers.collectAsState()
    val vacancies by homeViewModel.vacancies.collectAsState()
    val isDataLoaded by homeViewModel.isDataLoaded.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    var vacancyTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        SearchTopBar(
            R.drawable.ic_search_default,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Отображение предложений
        if (!isDataLoaded) {
            Spacer(modifier = Modifier.height(15.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(count = 4) { index ->
                    OfferItemPlaceHolder()
                }
            }
        } else if (!offers.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(15.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(offers) { offer ->
                    OfferItem(offer = offer)
                }
            }
        }

        // Отображение вакансий
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = Color.White,
            style = title1,
            text = "Вакансии для вас",
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(15.dp))
        if (!isDataLoaded) {
            repeat(3) {
                VacancyItemPlaceHolder()
                Spacer(modifier = Modifier.height(10.dp))
            }
        } else {
            vacancies.take(3).forEach { vacancy ->
                VacancyItem(
                    vacancy = vacancy,
                    onClick = {
                        navController.navigate("vacancy/${vacancy.id}")
                    },
                    onRespondClick = {
                        showBottomSheet = true
                        vacancyTitle = vacancy.title
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            ButtonBlue(
                textButton = "Еще ${vacancies.size} ${getDeclinedWord(vacancies.size, "вакансия", "вакансии", "вакансий")}",
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = {
                    navController.navigate("search")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        RespondBottomSheet(showBottomSheet, vacancyTitle) {
            showBottomSheet = false
        }
    }
}