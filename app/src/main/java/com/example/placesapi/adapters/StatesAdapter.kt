package com.example.placesapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.placesapi.databinding.StatesItemviewBinding
import com.example.placesapi.remote.data.StateResponseDto

class StatesAdapter(
    private val states: List<StateResponseDto.StateResponseDtoItem>,
    private val onItemClick: (Int) -> Unit
) : Adapter<StatesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            StatesItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return states.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val state = states.get(position)
        holder.binding.tvStatesName.text = state.name
        holder.binding.tvStatesId.text = state.id.toString()
        holder.binding.root.setOnClickListener {
            onItemClick(state.id ?: -1)
        }
    }

    inner class MyViewHolder(val binding: StatesItemviewBinding) :
        RecyclerView.ViewHolder(binding.root)
}

