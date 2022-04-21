package com.example.scheduleviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


    val service = Retrofit.Builder()
        .baseUrl("https://api.scheduledemo.schedgo.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ScheduleService::class.java) // pass in interface of requests

    val events = listOf<Event>()

    val adapter = EventListRecyclerViewAdapter(events)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = EventListRecyclerViewAdapter(events)

        findViewById<RecyclerView>(R.id.recyclerView).apply {

            this.adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(context)
        }


        findViewById<Switch>(R.id.officeHoursSwitch).apply {
            isChecked = true
            setOnCheckedChangeListener { _, checked ->
                updateList(checked)
            }
        }

        updateList(true)
    }

    private fun updateList(includesOfficeHours: Boolean) {
        service.listEvents(includesOfficeHours).enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                // update content in recyclerView with events in response
                adapter.updateEvents(response.body()!!)
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                throw t
            }

        })
    }
}