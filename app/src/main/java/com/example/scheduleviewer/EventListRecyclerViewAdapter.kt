package com.example.scheduleviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Abstract class
class EventListRecyclerViewAdapter(private var events: List<Event>)
    : RecyclerView.Adapter<EventListRecyclerViewAdapter.ViewHolder>() {

    fun updateEvents(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.eventTitleTextView)
        val typeTextView: TextView = itemView.findViewById(R.id.eventTypeTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.eventTimeTextView)
        val locationTextView: TextView = itemView.findViewById(R.id.eventLocationTextView)

    }

    // populate EventListItem UI
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.event_list_item, parent, false)

        // place in viewholder
        return ViewHolder(view)
    }

    // Recycle items and fill their data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // get specific event by index
        val event = events[position]

        holder.apply {
            titleTextView.text = event.title
            typeTextView.text = event.type
            timeTextView.text = "${event.start.toLocalDate()} ${event.start.toLocalTime()} - ${event.end.toLocalTime()}"
            locationTextView.text = event.location
        }

    }

    // help determine total # of items
    override fun getItemCount() = events.size
}