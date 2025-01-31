package com.example.presentation.ui.placeholders

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.Grey1
import com.example.presentation.ui.theme.Grey2
import com.example.presentation.ui.theme.text1
import com.example.presentation.ui.theme.title3

@Composable
fun VacancyItemPlaceHolder() {
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
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Grey1)
            .padding(16.dp)
            .graphicsLayer { this.alpha = alpha }
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
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(Grey2),
                    color = Color.Transparent,
                    style = text1,
                    text = "Сейчас просматривает 1 человек"
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(Grey2),
                    color = Color.Transparent,
                    style = title3,
                    text = "Сейчас просматривает 1 человек"
                )
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(Grey2),
            color = Color.Transparent,
            style = text1,
            text = "Санкт-петербург"
        )
        Spacer(modifier = Modifier.height(3.dp))
        Row(
            modifier = Modifier
        ) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(Grey2),
                color = Color.Transparent,
                style = text1,
                text = "Санкт-петербург"
            )
            Spacer(modifier = Modifier.width(5.dp))

        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(Grey2),
                color = Color.Transparent,
                style = text1,
                text = "Требуется опыт от 1 до 3 лет"
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(Grey2),
            color = Color.Transparent,
            style = text1,
            text = "Опубликовано 5 марта"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Grey2)
        )
    }
}