package com.example.scheduleviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.gson.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {

    // create custom Gson instance to use LocalDateTime class
    val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, object : JsonDeserializer<LocalDateTime> {
            // need deserialize for retrieving data
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): LocalDateTime {
                return LocalDateTime.parse(json!!.asString)
            }
        })
        .registerTypeAdapter(LocalDateTime::class.java, object : JsonSerializer<LocalDateTime> {
            override fun serialize(
                src: LocalDateTime?,
                typeOfSrc: Type?,
                context: JsonSerializationContext?
            ): JsonElement {
               return JsonPrimitive(src!!.toString())
            }
        })
        .create()

    // retrofit for http requestse
    val service = Retrofit.Builder()
        .baseUrl("https://api.scheduledemo.schedgo.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ScheduleService::class.java) // pass in interface of requests

    // database
    private lateinit var db: AppDatabase
    private lateinit var eventDao: EventDao

    var events = listOf<Event>()

    val adapter = EventListRecyclerViewAdapter(events)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "event=db")
            .allowMainThreadQueries()
            .build()
        eventDao = db.eventDao()

        events = eventDao.listAllEvents()

        if (events.isEmpty()) {
            getEventsFromServer()
            eventDao.addEvents(events)
            adapter.updateEvents(events)
        }

        findViewById<RecyclerView>(R.id.recyclerView).apply {

            this.adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(context)
        }


        findViewById<Switch>(R.id.officeHoursSwitch).apply {
            isChecked = true
            setOnCheckedChangeListener { _, checked ->
                events = updateList(checked)
                adapter.updateEvents(events)
            }
        }
        adapter.updateEvents(events)
    }

    private fun updateList(checked: Boolean): List<Event> {
        return when (checked) {
            true -> eventDao.listEventsOfType("Lecture")
            false -> eventDao.listAllEvents()
        }
    }

    private fun getEventsFromServer() {

        val includesOfficeHours = true
        service.listEvents(includesOfficeHours).enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                // update content in recyclerView with events in response
                events = response.body()!!
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                throw t
            }

        })
    }

}
