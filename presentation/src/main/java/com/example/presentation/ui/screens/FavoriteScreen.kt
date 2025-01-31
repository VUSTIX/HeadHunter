package com.example.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.ui.theme.Grey3
import com.example.presentation.ui.theme.text1
import com.example.presentation.ui.theme.title2
import com.example.presentation.ui.components.RespondBottomSheet
import com.example.presentation.ui.components.VacancyItem
import com.example.presentation.ui.placeholders.VacancyItemPlaceHolder
import com.example.presentation.viewmodel.FavoriteViewModel
import com.example.presentation.utils.getDeclinedWord

@Composable
fun FavoriteScreen(
    favoriteVacanciesViewModel: FavoriteViewModel = hiltViewModel(),
    navController: NavController
) {
    val favoriteVacancies by favoriteVacanciesViewModel.favoriteVacancies.collectAsState()
    val isDataLoaded by favoriteVacanciesViewModel.isDataLoaded.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    var vacancyTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = Color.White,
            style = title2,
            text = "Избранное"
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = Grey3,
            style = text1,
            text = "${favoriteVacancies.size} " +
                    getDeclinedWord(favoriteVacancies.size, "вакансия", "вакансии", "вакансий")
        )
        Spacer(modifier = Modifier.height(15.dp))

        if (!isDataLoaded) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(count = 3) { index ->
                    VacancyItemPlaceHolder()
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        } else if (favoriteVacancies.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = favoriteVacancies,
                    key = { vacancy -> vacancy.id }
                ) { vacancy ->
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
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    color = Grey3,
                    style = text1,
                    text = "У вас пока нет избранных вакансий."
                )
            }
        }

        RespondBottomSheet(showBottomSheet, vacancyTitle) {
            showBottomSheet = false
        }
    }
}