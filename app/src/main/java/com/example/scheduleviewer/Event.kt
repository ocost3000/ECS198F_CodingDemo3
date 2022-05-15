package com.example.scheduleviewer

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Event(
    var title: String,
    var type: String,
    var start: LocalDateTime,
    var end: LocalDateTime,
    var location: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

