package com.example.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val sfProDisplayFamily = FontFamily(
    Font(R.font.sf_pro_display_semibold, FontWeight.SemiBold),
    Font(R.font.sf_pro_display_medium, FontWeight.Medium),
    Font(R.font.sf_pro_display_regular, FontWeight.Normal)
)

val title1 = TextStyle(
    fontFamily = sfProDisplayFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 26.sp
)
val title2 = TextStyle(
    fontFamily = sfProDisplayFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp
)
val title3 = TextStyle(
    fontFamily = sfProDisplayFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp
)
val title4 = TextStyle(
    fontFamily = sfProDisplayFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp
)
val text1 = TextStyle(
    fontFamily = sfProDisplayFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)
val buttonText1 = TextStyle(
    fontFamily = sfProDisplayFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp
)
val buttonText2 = TextStyle(
    fontFamily = sfProDisplayFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)
val tabText = TextStyle(
    fontFamily = sfProDisplayFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 10.sp
)
val number = TextStyle(
    fontFamily = sfProDisplayFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 9.sp
)