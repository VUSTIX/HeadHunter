package com.example.presentation.utils

fun getDeclinedWord(
    count: Int,
    form1: String,
    form2: String,
    form3: String
): String {
    val form = when {
        count % 10 == 1 && count % 100 != 11 -> form1
        count % 10 in 2..4 && count % 100 !in 12..14 -> form2
        else -> form3
    }
    return form
}