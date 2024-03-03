package com.example.placesapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.placesapi.R
import com.example.placesapi.adapters.CityAdapter
import com.example.placesapi.databinding.ActivityCityBinding
import com.example.placesapi.viewmodel.CityViewModel

class CityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCityBinding
    private lateinit var viewModel: CityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val stateId = intent.getIntExtra("ID", -1)
        viewModel = CityViewModel()
        viewModel.getCities(stateId)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.cityResponse.observe(this) {
            val adapter = CityAdapter(it)
            with(binding) {
                progressBar.visibility = View.GONE
                recyclerView.adapter = adapter
            }
        }
    }
}