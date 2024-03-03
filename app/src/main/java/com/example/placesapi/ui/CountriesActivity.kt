package com.example.placesapi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.placesapi.R
import com.example.placesapi.adapters.CountriesAdapter
import com.example.placesapi.databinding.ActivityCountriesBinding
import com.example.placesapi.viewmodel.CountriesViewModel

class CountriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountriesBinding
    private lateinit var viewModel: CountriesViewModel
    private lateinit var adapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = CountriesViewModel()
        setupUI()
        observeLiveData()
    }

    private fun setupUI() {
        viewModel.getCountries()
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(this@CountriesActivity)
        }
    }

    private fun observeLiveData() {
        viewModel.countriesResponse.observe(this) {
            adapter = CountriesAdapter(it) { id ->
                val intent = Intent(this, StatesActivity::class.java).apply {
                    putExtra("ID", id)
                }
                startActivity(intent)
            }
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.adapter = adapter
        }
    }
}