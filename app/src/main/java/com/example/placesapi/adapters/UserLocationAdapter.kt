package com.example.placesapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.placesapi.R
import com.example.placesapi.databinding.ItemUserLocationBinding
import com.example.placesapi.remote.data.Location
import com.google.android.gms.maps.model.LatLng

// com.example.places-api.adapters.UserNamesAdapter.kt
class UserLocationAdapter(
    private val locations: List<Location>,
    private val onTrackClick: (LatLng) -> Unit
) : RecyclerView.Adapter<UserLocationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserLocationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = locations[position]
        with(holder.binding) {
            userNameTextView.text = item.name
            btnTrack.setOnClickListener {
                onTrackClick(
                    LatLng(item.latitude, item.longitude)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    inner class ViewHolder(val binding: ItemUserLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}
