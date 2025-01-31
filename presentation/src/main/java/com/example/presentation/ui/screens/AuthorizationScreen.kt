package com.example.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.presentation.ui.theme.Blue
import com.example.presentation.ui.theme.Grey1
import com.example.presentation.ui.theme.Grey2
import com.example.presentation.ui.theme.Grey3
import com.example.presentation.ui.theme.buttonText2
import com.example.presentation.ui.theme.title2
import com.example.presentation.ui.theme.title3
import com.example.presentation.ui.theme.title4
import com.example.presentation.ui.components.ButtonGreen1


@Composable
fun AuthorizationScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            color = Color.White,
            style = title2,
            text = "Вход в личный кабинет"
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            FrameWorker(navController)
            Spacer(modifier = Modifier.height(15.dp))
            FrameEmployer()
        }
    }
}

@Composable
fun FrameWorker(navController: NavHostController) {
    var value by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Grey1)
            .padding(16.dp)
    ) {
        Text(
            color = Color.White,
            style = title3,
            text = "Поиск работы"
        )
        Spacer(modifier = Modifier.height(20.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Grey2)
                .padding(horizontal = 10.dp),
            value = value,
            maxLines = 1,
            singleLine = true,
            onValueChange = { value = it },
            textStyle = title3.copy(
                color = Color.White,
            ),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = "Введите почту",
                            style = title4.copy(
                                color = Grey3
                            )
                        )
                    }
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = {
                    navController.navigate("confirm_screen")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Blue),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(0.dp),
            ) {
                Text(
                    color = Color.White,
                    style = buttonText2,
                    text = "Продолжить"
                )
            }
            TextButton(
                modifier = Modifier
                    .weight(1f),
                onClick = {  },
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    color = Blue,
                    style = buttonText2,
                    text = "Войти с паролем"
                )
            }
        }
    }
}


@Composable
fun FrameEmployer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Grey1)
            .padding(16.dp)
    ) {
        Text(
            color = Color.White,
            style = title3,
            text = "Поиск сотрудников"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            color = Color.White,
            style = buttonText2,
            text = "Размещение вакансий и доступ к базе резюме"
        )
        Spacer(modifier = Modifier.height(20.dp))
        ButtonGreen1(
            textButton = "Я ищу сотрудников",
            onClick = { }
        )
    }
}