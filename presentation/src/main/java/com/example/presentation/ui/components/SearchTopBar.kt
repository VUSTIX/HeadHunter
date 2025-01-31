package com.example.presentation.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.Grey2
import com.example.presentation.ui.theme.Grey3
import com.example.presentation.ui.theme.title4
import com.example.presentation.R

@Composable
fun SearchTopBar(
    iconId: Int,
    modifier: Modifier
) {
    var value by remember { mutableStateOf("") }
    val activity = (LocalContext.current as? ComponentActivity)

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextField(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Grey2)
                .padding(horizontal = 10.dp),
            value = value,
            onValueChange = { value = it },
            textStyle = title4.copy(
                color = Color.White,
            ),
            maxLines = 1,
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                if(iconId == R.drawable.ic_left_arrow) {
                                    activity?.onBackPressedDispatcher?.onBackPressed()
                                }
                            },
                        painter = painterResource(id = iconId),
                        contentDescription = "Custom Icon"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box {
                        if (value.isEmpty()) {
                            Text(
                                text = "Должность, ключевые слова",
                                style = title4.copy(
                                    color = Grey3
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Grey2),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "Custom Icon"
            )
        }
    }
}