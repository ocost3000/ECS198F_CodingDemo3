package com.example.scheduleviewer

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDateTime

@Database(entities = [Event::class], version = 1)
@TypeConverters(AppDatabase.LocalDateTimeTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    class LocalDateTimeTypeConverters {
        @TypeConverter
        fun fromString(s: String): LocalDateTime = LocalDateTime.parse(s)
        @TypeConverter
        fun toString(ldt: LocalDateTime): String = ldt.toString()
    }
}