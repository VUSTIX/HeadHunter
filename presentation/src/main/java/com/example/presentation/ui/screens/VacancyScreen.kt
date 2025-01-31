package com.example.presentation.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.model.local.Favorite
import com.example.domain.model.remote.Vacancy
import com.example.presentation.R
import com.example.presentation.ui.components.ButtonGreen2
import com.example.presentation.ui.components.ButtonQuestion
import com.example.presentation.ui.components.RespondBottomSheet
import com.example.presentation.ui.theme.DarkGreen
import com.example.presentation.ui.theme.Grey1
import com.example.presentation.ui.theme.Grey2
import com.example.presentation.ui.theme.Grey3
import com.example.presentation.ui.theme.Grey5
import com.example.presentation.ui.theme.text1
import com.example.presentation.ui.theme.title1
import com.example.presentation.ui.theme.title2
import com.example.presentation.ui.theme.title3
import com.example.presentation.ui.theme.title4
import com.example.presentation.utils.getDeclinedWord
import com.example.presentation.viewmodel.FavoriteViewModel
import com.example.presentation.viewmodel.VacancyViewModel

@Composable
fun VacancyScreen(
    viewModel: VacancyViewModel,
    navController: NavController
) {
    val vacancy by viewModel.vacancy.collectAsState()
    val isDataLoaded by viewModel.isDataLoaded.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    var vacancyTitle by remember { mutableStateOf("") }

    if (!isDataLoaded) {
        VacancyScreenPlaceHolder()
    } else {
        vacancy?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                VacancyTopBar(vacancy = vacancy!!)
                Spacer(modifier = Modifier.height(15.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    VacancyHeader(
                        title = it.title,
                        salary = it.salary.full,
                        experience = it.experience.text,
                        schedules = it.schedules
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    VacancyInfo(
                        appliedNumber = it.appliedNumber,
                        lookingNumber = it.lookingNumber
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    MapInfo(
                        company = it.company,
                        town = it.address.town,
                        street = it.address.street,
                        house = it.address.house
                    )
                    it.description?.let {
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            color = Color.White,
                            style = text1,
                            text = it
                        )
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        color = Color.White,
                        style = title2,
                        text = "Ваши задачи"
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        color = Color.White,
                        style = text1,
                        text = it.responsibilities
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        color = Color.White,
                        style = title4,
                        text = "Задайте вопрос работадателю"
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        color = Grey3,
                        style = title4,
                        text = "Он получит его с откликом на вакансию"
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    it.questions.forEach { question ->
                        ButtonQuestion(textButton = question)
                        Spacer(modifier = Modifier.height(7.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Column {
                    Spacer(modifier = Modifier.height(5.dp))
                    ButtonGreen2(
                        onClick = {
                            showBottomSheet = true
                            vacancyTitle = vacancy!!.title
                        })
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
        RespondBottomSheet(showBottomSheet, vacancy!!.title) {
            showBottomSheet = false
        }
    }

}

@Composable
fun VacancyTopBar(
    favoriteVacanciesViewModel: FavoriteViewModel = hiltViewModel(),
    vacancy: Vacancy
) {
    val activity = LocalContext.current as? ComponentActivity
    val allFavoritesVacancies by favoriteVacanciesViewModel.allFavoritesVacancies.collectAsState()

    val favoritesVacancy = Favorite(
        vacancyId = vacancy.id,
        title = vacancy.title,
        town = vacancy.address.town,
        company = vacancy.company,
        experience = vacancy.experience.previewText,
        publishedDate = vacancy.publishedDate
    )

    var isFavorites = allFavoritesVacancies.any { favoritesVacancy ->
        favoritesVacancy.vacancyId == vacancy.id
    }

    val iconFavorites = if (isFavorites) {
        R.drawable.ic_favorites
    } else {
        R.drawable.ic_heart_default
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    activity?.onBackPressedDispatcher?.onBackPressed()
                },
            painter = painterResource(id = R.drawable.ic_left_arrow),
            contentDescription = "Custom Icon"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(28.dp),
                painter = painterResource(id = R.drawable.ic_eye_active),
                contentDescription = "Custom Icon"
            )
            Image(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_share),
                contentDescription = "Custom Icon"
            )
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        if(isFavorites) {
                            favoriteVacanciesViewModel.removeFavoriteVacancyById(vacancyId = vacancy.id)
                        } else {
                            favoriteVacanciesViewModel.addFavoriteVacancy(favoritesVacancy)
                        }
                        isFavorites = !isFavorites
                    },
                painter = painterResource(id = iconFavorites),
                contentDescription = "Custom Icon"
            )
        }
    }
}

@Composable
fun VacancyHeader(
    title: String,
    salary: String,
    experience: String,
    schedules: List<String>,
) {
    Column {
        Text(
            color = Color.White,
            style = title1,
            text = title
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            color = Color.White,
            style = text1,
            text = salary
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            color = Color.White,
            style = text1,
            text = "Требуемый опыт: $experience"
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            color = Color.White,
            style = text1,
            text = schedules.joinToString(separator = ", ")
        )
    }
}

@Composable
fun VacancyInfo(
    appliedNumber: Int?,
    lookingNumber: Int?
) {
    Row {
        appliedNumber?.let {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(DarkGreen)
                    .padding(vertical = 10.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    color = Color.White,
                    style = text1,
                    text = "$it " +
                            getDeclinedWord(it, "человек", "человека", "человек") +
                            " уже\nоткликнулись"
                )
                Image(
                    modifier = Modifier
                        .size(16.dp),
                    painter = painterResource(id = R.drawable.ic_responded),
                    contentDescription = "Custom Icon"
                )
            }
            lookingNumber?.let {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        lookingNumber?.let {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(DarkGreen)
                    .padding(vertical = 10.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    color = Color.White,
                    style = text1,
                    text = "$it " +
                            getDeclinedWord(
                                it,
                                "человек\nсейчас смотрит",
                                "человека\nсейчас смотрят",
                                "человек\nсейчас смотрит"
                            )
                )
                Image(
                    modifier = Modifier
                        .size(16.dp),
                    painter = painterResource(id = R.drawable.ic_watching),
                    contentDescription = "Custom Icon"
                )
            }
        }
    }
}

@Composable
fun MapInfo(
    company: String,
    town: String,
    street: String,
    house: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(134.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Grey1)
            .padding(vertical = 10.dp, horizontal = 15.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                color = Color.White,
                style = title3,
                text = company
            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(id = R.drawable.ic_check_mark),
                contentDescription = "Custom Icon"
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(53.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Grey5)
                .padding(vertical = 10.dp, horizontal = 8.dp)
        ) { }
        Text(
            color = Color.White,
            style = text1,
            text = "$town, $street, $house"
        )
    }
}

@Composable
fun VacancyScreenPlaceHolder() {

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        //VacancyTopBar()
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .graphicsLayer { this.alpha = alpha }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Grey2),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Grey2),
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Grey2),
            )
            Spacer(modifier = Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Grey2),
            )
            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(134.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Grey2),
            )
            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomStart = 0.dp, bottomEnd = 5.dp))
                    .background(Grey2),
            )
        }
    }
}