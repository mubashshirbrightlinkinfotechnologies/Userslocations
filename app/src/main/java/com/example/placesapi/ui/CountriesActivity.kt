package com.example.placesapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.placesapi.databinding.ActivityCountriesBinding
import com.example.placesapi.viewmodel.PlacesViewModel

class CountriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountriesBinding
    private lateinit var viewModel: PlacesViewModel
    private val countriesSpinnerList = mutableListOf<String>()
    val statesSpinnerList = mutableListOf<String>()
    private val citySpinnerList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = PlacesViewModel()
        setupUI()
        observeLiveData()
    }

    private fun setupUI() {
        viewModel.getCountries()
        with(binding) {
            spinnerCountries.onItemSelectedListener = object : AdapterView
                .OnItemClickListener, AdapterView.OnItemSelectedListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //TODO("Not yet implemented")
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.getStates(position+1)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //TODO("Not yet implemented")
                }
            }

            spinnerStates.onItemSelectedListener = object : AdapterView
            .OnItemClickListener, AdapterView.OnItemSelectedListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //TODO("Not yet implemented")
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.getCities(
                        viewModel.statesResponse.value?.find {
                            it.name == statesSpinnerList[position]
                        }?.id ?: 0
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //TODO("Not yet implemented")
                }
            }
        }
    }

    private fun observeLiveData() {
        viewModel.countriesResponse.observe(this) {
            it.map { items ->
                items.name?.let { it1 -> countriesSpinnerList.add(it1) }

            }
            val arrayAdapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, countriesSpinnerList)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCountries.adapter = arrayAdapter
            binding.progressBar.visibility = View.GONE
        }

        viewModel.statesResponse.observe(this){response ->
            statesSpinnerList.clear()
            response.map { states ->
                states.name?.let { statesSpinnerList.add(it) }
            }

            val arrayAdapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, statesSpinnerList)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerStates.adapter = arrayAdapter
        }

        viewModel.cityResponse.observe(this){response ->
            citySpinnerList.clear()
            response.map { city ->
                city.name?.let { citySpinnerList.add(it) }
            }

            val arrayAdapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, citySpinnerList)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCity.adapter = arrayAdapter
        }
    }
}