package com.example.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.Grey2
import com.example.presentation.ui.theme.title4

@Composable
fun ButtonQuestion(
    textButton: String
) {
    Button(
        modifier = Modifier
            .height(33.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Grey2),
        shape = RoundedCornerShape(50.dp),
        contentPadding = PaddingValues(horizontal = 15.dp),
        onClick = {  }
    ) {
        Text(
            color = Color.White,
            style = title4,
            text = textButton
        )
    }
}