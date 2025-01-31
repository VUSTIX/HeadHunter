package com.example.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.local.Favorite
import com.example.domain.model.remote.Vacancy
import com.example.presentation.ui.theme.Green
import com.example.presentation.ui.theme.Grey1
import com.example.presentation.ui.theme.Grey3
import com.example.presentation.ui.theme.text1
import com.example.presentation.ui.theme.title3
import com.example.presentation.R
import com.example.presentation.viewmodel.FavoriteViewModel
import com.example.presentation.utils.getDeclinedWord
import com.example.presentation.utils.getFormatDate

@Composable
fun VacancyItem(
    favoritesVacanciesViewModel: FavoriteViewModel = hiltViewModel(),
    vacancy: Vacancy,
    onClick: () -> Unit,
    onRespondClick: () -> Unit
) {
    val allFavoriteVacancies by favoritesVacanciesViewModel.allFavoritesVacancies.collectAsState()

    val favoriteVacancy = Favorite(
        vacancyId = vacancy.id,
        title = vacancy.title,
        town = vacancy.address.town,
        company = vacancy.company,
        experience = vacancy.experience.previewText,
        publishedDate = vacancy.publishedDate
    )

    // Изменяем var на val и убираем локальное изменение
    val isFavorites = allFavoriteVacancies.any { favorite ->
        favorite.vacancyId == vacancy.id
    }

    val iconFavorites = if (isFavorites) {
        R.drawable.ic_favorites
    } else {
        R.drawable.ic_heart_default
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Grey1)
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                vacancy.lookingNumber?.let {
                    Text(
                        color = Green,
                        style = text1,
                        text = "Сейчас просматривает $it " +
                                getDeclinedWord(it, "человек", "человека", "человек")
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
                Text(
                    color = Color.White,
                    style = title3,
                    text = vacancy.title
                )
            }
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        if(isFavorites) {
                            favoritesVacanciesViewModel.removeFavoriteVacancyById(vacancyId = vacancy.id)
                        } else {
                            favoritesVacanciesViewModel.addFavoriteVacancy(favoriteVacancy)
                        }
                        // Убираем изменение isFavorites локально
                    },
                painter = painterResource(id = iconFavorites),
                contentDescription = "Custom Icon"
            )
        }
        // Остальная часть UI остаётся без изменений
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            color = Color.White,
            style = text1,
            text = vacancy.address.town
        )
        Spacer(modifier = Modifier.height(3.dp))
        Row(
            modifier = Modifier
        ) {
            Text(
                color = Color.White,
                style = text1,
                text = vacancy.company
            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(id = R.drawable.ic_check_mark),
                contentDescription = "Custom Icon"
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(id = R.drawable.ic_experience),
                contentDescription = "Custom Icon"
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                color = Color.White,
                style = text1,
                text = vacancy.experience.previewText
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            color = Grey3,
            style = text1,
            text = "Опубликовано ${getFormatDate(vacancy.publishedDate)}"
        )
        Spacer(modifier = Modifier.height(20.dp))
        ButtonGreen1(
            "Откликнуться",
            onClick = onRespondClick
        )
    }
}