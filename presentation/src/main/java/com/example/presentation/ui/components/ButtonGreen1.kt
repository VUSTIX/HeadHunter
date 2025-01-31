package com.example.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.Green
import com.example.presentation.ui.theme.buttonText2

@Composable
fun ButtonGreen1(
    textButton: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Green),
        shape = RoundedCornerShape(50.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Text(
            color = Color.White,
            style = buttonText2,
            text = textButton
        )
    }
}