package com.example.scheduleviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Abstract class
class EventListRecyclerViewAdapter(private var events: List<Event>)
    : RecyclerView.Adapter<EventListRecyclerViewAdapter.ViewHolder>() {

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


    }

    // help determine total # of items
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}