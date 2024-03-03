package com.example.placesapi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.placesapi.R
import com.example.placesapi.adapters.StatesAdapter
import com.example.placesapi.databinding.ActivityStatesBinding
import com.example.placesapi.viewmodel.StatesViewModel

class StatesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatesBinding
    private lateinit var viewModel: StatesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val countryID = intent.getIntExtra("ID", -1)
        viewModel = StatesViewModel()
        viewModel.getStates(countryID)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.statesResponse.observe(this) { response ->
            val adapter = StatesAdapter(response) { id ->
                val intent = Intent(this, CityActivity::class.java).apply {
                    putExtra("ID", id)
                }
                startActivity(intent)
            }
            with(binding) {
                progressBar.visibility = View.GONE
                recyclerView.adapter = adapter
            }
        }
    }
}