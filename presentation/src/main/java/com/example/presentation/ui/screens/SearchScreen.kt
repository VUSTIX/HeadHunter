package com.example.presentation.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.ui.theme.Blue
import com.example.presentation.ui.theme.Grey2
import com.example.presentation.ui.theme.text1
import com.example.presentation.R
import com.example.presentation.ui.components.RespondBottomSheet
import com.example.presentation.ui.components.SearchTopBar
import com.example.presentation.ui.components.VacancyItem
import com.example.presentation.ui.placeholders.VacancyItemPlaceHolder
import com.example.presentation.viewmodel.SearchViewModel
import com.example.presentation.utils.getDeclinedWord

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    val vacancies by viewModel.vacancies.collectAsState()
    val isDataLoaded by viewModel.isDataLoaded.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    var vacancyTitle by remember { mutableStateOf("") }

    val scrollState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            SearchTopBar(
                R.drawable.ic_left_arrow,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    if (!isDataLoaded) {
                        SearchHeaderPlaceHolder()
                    } else {
                        SearchHeader(vacancies.size)
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                }
                if (!isDataLoaded) {
                    items(5) {
                        VacancyItemPlaceHolder()
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                } else {
                    items(vacancies) { vacancy ->
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
                item {
                    Spacer(modifier = Modifier.height(65.dp))
                }
            }
            RespondBottomSheet(showBottomSheet, vacancyTitle) {
                showBottomSheet = false
            }
        }
    }

}

@Composable
fun SearchHeader(
    vacancySize: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            color = Color.White,
            style = text1,
            text = "$vacancySize ${getDeclinedWord(vacancySize, "вакансия", "вакансии", "вакансий")}"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                color = Blue,
                style = text1,
                text = "По соответствию"
            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(id = R.drawable.ic_sorting),
                contentDescription = "Custom Icon"
            )
        }
    }
}

@Composable
fun SearchHeaderPlaceHolder() {

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .graphicsLayer { this.alpha = alpha },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(Grey2),
            color = Color.Transparent,
            style = text1,
            text = "147 вакансий"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(Grey2),
                color = Color.Transparent,
                style = text1,
                text = "По соответствию"
            )
        }
    }
}