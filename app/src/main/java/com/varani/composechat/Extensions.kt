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
fun LocalDateTime.toSectioningLabel(): String = run {

    val formatter = DateTimeFormatter.ofPattern("EEEE H:mm")
    format(formatter)
}

fun String.toAnnotatedString(): AnnotatedString = run {
    val dayOfTheWeek = this.split(" ")
    AnnotatedString(
        text = dayOfTheWeek[0],
        spanStyle = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp)
    ).plus(
        AnnotatedString(" ")
    ).plus(
        AnnotatedString(
            text = dayOfTheWeek[1],
            spanStyle = SpanStyle(fontSize = 12.sp)
        )
    )
}