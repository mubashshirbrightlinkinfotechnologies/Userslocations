package com.example.placesapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.placesapi.databinding.CityItemviewBinding
import com.example.placesapi.remote.data.CityResponseDto

class CityAdapter(private val city: List<CityResponseDto.CityResponseDtoItem>) :
    Adapter<CityAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CityItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return city.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val city = city.get(position)
        holder.binding.tvCityName.text = city.name
        holder.binding.tvCityId.text = city.id.toString()
    }

    inner class MyViewHolder(val binding: CityItemviewBinding) :
        RecyclerView.ViewHolder(binding.root)
}

