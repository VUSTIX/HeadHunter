package com.example.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.Green
import com.example.presentation.ui.theme.Grey0
import com.example.presentation.ui.theme.Grey1
import com.example.presentation.ui.theme.Grey2
import com.example.presentation.ui.theme.Grey3
import com.example.presentation.ui.theme.buttonText1
import com.example.presentation.ui.theme.text1
import com.example.presentation.ui.theme.title3
import com.example.presentation.ui.theme.title4
import com.example.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RespondBottomSheet(
    showBottomSheet: Boolean,
    vacancyTitle: String,
    onDismiss: () -> Unit
) {
    var showTextField by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var value by remember { mutableStateOf("") }

    if (showBottomSheet) {
        ModalBottomSheet(
            containerColor = Grey0,
            onDismissRequest = {
                showTextField = false
                value = ""
                onDismiss()
            },
            sheetState = modalBottomSheetState,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(50.dp)),
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "Custom Icon"
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        modifier = Modifier
                            .height(48.dp),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            color = Grey3,
                            style = text1,
                            text = "Резюме для отклика"
                        )
                        Text(
                            color = Color.White,
                            style = title3,
                            text = vacancyTitle
                        )
                    }
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Grey1))
                Spacer(modifier = Modifier.height(25.dp))
                if (showTextField) {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(85.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Grey2)
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                            .focusRequester(focusRequester),
                        value = value,
                        onValueChange = { value = it },
                        textStyle = title4.copy(
                            color = Color.White,
                        ),
                        decorationBox = { innerTextField ->
                            Row {
                                Box {
                                    if (value.isEmpty()) {
                                        Text(
                                            text = "Ваше сопроводительное письмо",
                                            style = text1.copy(
                                                color = Grey3
                                            )
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        }
                    )
                    LaunchedEffect(Unit) {
                        focusRequester.requestFocus()
                    }
                } else {
                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(0.dp),
                        onClick = {
                            showTextField = true
                        }
                    ) {
                        Text(
                            color = Green,
                            style = buttonText1,
                            text = "Добавить сопроводительное"
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    ButtonGreen2(
                        onClick = {
                            showTextField = false
                            value = ""
                            onDismiss()
                        })
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
        }
    }
}