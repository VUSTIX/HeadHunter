package com.example.presentation.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.presentation.ui.theme.Grey2
import com.example.presentation.ui.theme.Grey3
import com.example.presentation.ui.theme.title1
import com.example.presentation.ui.theme.title2
import com.example.presentation.ui.theme.title3
import com.example.presentation.ui.components.ButtonBlue
import com.example.presentation.utils.saveConfirmationStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ConfirmScreen(
    navController: NavHostController,
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 162.dp, end = 16.dp)
    ) {
        Text(
            color = Color.White,
            style = title2,
            text = "Отправили код на example@mail.ru"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            color = Color.White,
            style = title3,
            text = "Напишите его, чтобы подтвердить что это вы, " +
                    "а не кто-то другой входит в личный кабинет"
        )
        Spacer(modifier = Modifier.height(15.dp))
        CodeNumber()
        Spacer(modifier = Modifier.height(15.dp))
        ButtonBlue(
            textButton = "Подтвердить",
            modifier = Modifier,
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    saveConfirmationStatus(context, true)
                    withContext(Dispatchers.Main) {
                        navController.navigate("home_graph") {
                            popUpTo("auth_graph") { inclusive = true }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun CodeNumber() {
    val focusRequesters = List(4) { FocusRequester() }
    val codeValues = remember { mutableStateListOf("", "", "", "") }

    Row {
        for (i in 0 until 4) {
            CodeSample(
                value = codeValues[i],
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        codeValues[i] = newValue
                        if (newValue.length == 1 && i < 3) {
                            focusRequesters[i + 1].requestFocus()
                        }
                    }
                },
                onDelete = {
                    if (codeValues[i].isEmpty() && i > 0) {
                        focusRequesters[i - 1].requestFocus()
                        codeValues[i - 1] = ""
                    }
                },
                focusRequester = focusRequesters[i],
                autoFocus = i == 0
            )
            if (i < 3) Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun CodeSample(
    value: String,
    onValueChange: (String) -> Unit,
    onDelete: () -> Unit,
    focusRequester: FocusRequester,
    autoFocus: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false) }

    LaunchedEffect(autoFocus) {
        if (autoFocus) {
            focusRequester.requestFocus()
        }
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = title3.copy(
            color = Color.White,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Grey2)
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
            .onKeyEvent { event ->
                if (event.key == Key.Backspace && event.type == KeyEventType.KeyUp) {
                    if (value.isEmpty()) {
                        onDelete()
                    }
                    true
                } else {
                    false
                }
            },
        decorationBox = { innerTextField ->
            Box(
                contentAlignment = Alignment.Center
            ) {
                if (value.isEmpty() && !isFocused) {
                    Text(
                        text = "*",
                        style = title1.copy(
                            color = Grey3
                        )
                    )
                }
                innerTextField()
            }
        }
    )
}