package com.example.scheduleviewer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventDao {
    @Query("SELECT * FROM event ORDER BY 'from'")
    fun listAllEvents(): List<Event>

    @Query("SELECT * FROM event WHERE type=:type ORDER BY 'from'")
    fun listEventsOfType(type: String): List<Event>

    @Insert
    fun addEvent(event: Event)

    @Insert
    fun addEvents(events: List<Event>)

    @Delete
    fun removeEvent(event: Event)

    @Query("DELETE FROM event WHERE type=:type")
    fun removeEventsOfType(type: String)
}