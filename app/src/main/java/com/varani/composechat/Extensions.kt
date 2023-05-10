package com.varani.composechat

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Ana Varani on 10/05/2023.
 */
fun LocalDateTime.toSectioningLabel(): AnnotatedString = run {

    val formatter = DateTimeFormatter.ofPattern("H:mm")
    val time = format(formatter)

    AnnotatedString(
        text = dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() },
        spanStyle = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp)
    ).plus(
        AnnotatedString(" ")
    ).plus(
        AnnotatedString(
            text = time,
            spanStyle = SpanStyle(fontSize = 12.sp)
        )
    )
}

fun String.toAnnotatedString(): AnnotatedString = run {
    AnnotatedString(
        this
    )
}