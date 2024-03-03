package com.example.placesapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.placesapi.databinding.CountriesItemviewBinding
import com.example.placesapi.remote.data.CountryResponseDto

class CountriesAdapter(
    private val countries: List<CountryResponseDto.CountryResponseDtoItem>,
    private val onItemClick: (id: Int) -> Unit
) :
    Adapter<CountriesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CountriesItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val country = countries.get(position)
        holder.binding.tvCountryName.text = country.name
        holder.binding.tvCountryId.text = country.id.toString()
        holder.binding.root.setOnClickListener {
            onItemClick(country.id ?: -1)
        }
    }

    inner class MyViewHolder(val binding: CountriesItemviewBinding) :
        RecyclerView.ViewHolder(binding.root)
}

