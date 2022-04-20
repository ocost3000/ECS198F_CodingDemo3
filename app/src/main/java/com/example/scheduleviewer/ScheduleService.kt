package com.example.scheduleviewer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleService {
    // Define endpoints
    @GET("/events")
    fun listEvents(@Query("includesOfficeHours") includesOfficeHours: Boolean): Call<List<Event>>
}