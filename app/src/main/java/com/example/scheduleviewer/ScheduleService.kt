package com.example.scheduleviewer

import retrofit2.Call
import retrofit2.http.*

interface ScheduleService {
    // Define endpoints

    // GET request collects all the taco truck Events
    @GET("/events")
    fun listEvents(@Query("includesOfficeHours") includesOfficeHours: Boolean): Call<List<Event>>

    // POST response has no body, Unit is the kotlin way of saying void type
    @POST("/events")
    fun createEvent(@Body event: Event): Call<Unit>

    // GET request retrieves details of one truck
    @GET("/events/{id}")
    fun getEvent(@Path("id") id: Int): Call<Event>

    // DELETE an item
    @GET("events/{id}")
    fun deleteEvent(@Path("id") id: Int): Call<Unit>
}