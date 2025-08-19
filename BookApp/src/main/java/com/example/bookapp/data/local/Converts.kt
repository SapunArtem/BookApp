package com.example.bookapp.data.local

import androidx.room.TypeConverter

class Converts {
    @TypeConverter
    fun fromList(list: List<String>): String = list.joinToString("|")

    @TypeConverter
    fun toList(string: String): List<String> =
        if (string.isEmpty()) emptyList() else string.split("|")

    @TypeConverter
    fun fromBoolean(value: Boolean): Int = if (value) 1 else 0

    @TypeConverter
    fun toBoolean(value: Int): Boolean = value == 1
}