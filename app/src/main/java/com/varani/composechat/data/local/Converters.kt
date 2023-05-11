package com.varani.composechat.data.local

import androidx.room.TypeConverter
import java.time.LocalDateTime


/**
 * Created by Ana Varani on 11/05/2023.
 */
object Converters {

    @TypeConverter
    fun toLocalDateTime(dateString: String?): LocalDateTime? {
        return dateString?.let {
            LocalDateTime.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateTimeString(date: LocalDateTime?): String? {
        return date?.toString()
    }
}