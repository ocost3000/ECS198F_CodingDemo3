package com.example.scheduleviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    val events = listOf(
        Event(
            "ECS 198F Android App Dev",
            "Lecture",
            LocalDateTime.of(2022, 4, 4, 16, 10),
            LocalDateTime.of(2022, 4, 4, 17, 0),
            "Wellman Hall 212"
        ),
        Event(
            "ECS 198F Android App Dev",
            "Lecture",
            LocalDateTime.of(2022, 4, 6, 16, 10),
            LocalDateTime.of(2022, 4, 6, 17, 0),
            "Wellman Hall 212"
        ),
        Event(
            "ECS 198F Android App Dev",
            "Office Hour",
            LocalDateTime.of(2022, 4, 5, 10, 30),
            LocalDateTime.of(2022, 4, 5, 11, 45),
            "Zoom"
        ),
        Event(
            "ECS 198F Android App Dev",
            "Office Hour",
            LocalDateTime.of(2022, 4, 7, 10, 30),
            LocalDateTime.of(2022, 4, 7, 11, 45),
            "Zoom"
        ),
        Event(
            "ECS 122B Algorithm Design and Analysis",
            "Lecture",
            LocalDateTime.of(2022, 4, 4, 11, 0),
            LocalDateTime.of(2022, 4, 4, 11, 50),
            "Veihmeyer Hall 212"
        ),
        Event(
            "ECS 122B Algorithm Design and Analysis",
            "Lecture",
            LocalDateTime.of(2022, 4, 6, 11, 0),
            LocalDateTime.of(2022, 4, 6, 11, 50),
            "Veihmeyer Hall 212"
        ),
        Event(
            "ECS 122B Algorithm Design and Analysis",
            "Lecture",
            LocalDateTime.of(2022, 4, 8, 11, 0),
            LocalDateTime.of(2022, 4, 8, 11, 50),
            "Veihmeyer Hall 212"
        ),
        Event(
            "ECS 122B Algorithm Design and Analysis",
            "Office Hour",
            LocalDateTime.of(2022, 4, 5, 8, 0),
            LocalDateTime.of(2022, 4, 5, 9, 0),
            "Zoom"
        ),
        Event(
            "ECS 122B Algorithm Design and Analysis",
            "Office Hour",
            LocalDateTime.of(2022, 4, 7, 9, 0),
            LocalDateTime.of(2022, 4, 7, 10, 0),
            "Zoom"
        ),
        Event(
            "ECS 162 Web Programming",
            "Lecture",
            LocalDateTime.of(2022, 4, 4, 9, 0),
            LocalDateTime.of(2022, 4, 4, 9, 50),
            "Social Sciences & Humanities 1100"
        ),
        Event(
            "ECS 162 Web Programming",
            "Lecture",
            LocalDateTime.of(2022, 4, 6, 9, 0),
            LocalDateTime.of(2022, 4, 6, 9, 50),
            "Social Sciences & Humanities 1100"
        ),
        Event(
            "ECS 162 Web Programming",
            "Lecture",
            LocalDateTime.of(2022, 4, 8, 9, 0),
            LocalDateTime.of(2022, 4, 8, 9, 50),
            "Social Sciences & Humanities 1100"
        ),
        Event(
            "ECS 162 Web Programming",
            "Office Hour",
            LocalDateTime.of(2022, 4, 4, 11, 0),
            LocalDateTime.of(2022, 4, 4, 12, 0),
            "Zoom"
        ),
        Event(
            "ECS 152A Computer Networks",
            "Lecture",
            LocalDateTime.of(2022, 4, 4, 14, 10),
            LocalDateTime.of(2022, 4, 4, 15, 0),
            "Giedt 1003"
        ),
        Event(
            "ECS 152A Computer Networks",
            "Lecture",
            LocalDateTime.of(2022, 4, 6, 14, 10),
            LocalDateTime.of(2022, 4, 6, 15, 0),
            "Giedt 1003"
        ),
        Event(
            "ECS 152A Computer Networks",
            "Lecture",
            LocalDateTime.of(2022, 4, 8, 14, 10),
            LocalDateTime.of(2022, 4, 8, 15, 0),
            "Giedt 1003"
        ),
        Event(
            "ECS 152A Computer Networks",
            "Office Hour",
            LocalDateTime.of(2022, 4, 4, 13, 0),
            LocalDateTime.of(2022, 4, 4, 14, 0),
            "Kemper 3043"
        ),
    ).sortedBy { it.from }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = EventListRecyclerViewAdapter(events)

        findViewById<RecyclerView>(R.id.recyclerView).apply {

            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }


        findViewById<Switch>(R.id.officeHoursSwitch).apply {
            isChecked = true
            setOnCheckedChangeListener { _, checked ->
                adapter.updateEvents(when (checked) {
                    true -> events
                    false -> events.filter { it.type != "Office Hour" }
                })
            }

        }

    }
}