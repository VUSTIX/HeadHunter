package com.example.presentation.utils

fun getFormatDate(
    date: String
): String {
    val months = arrayOf(
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
    )

    val parts = date.split("-")
    val day = parts[2].toInt()
    val month = parts[1].toInt()

    return "$day ${months[month - 1]}"
}