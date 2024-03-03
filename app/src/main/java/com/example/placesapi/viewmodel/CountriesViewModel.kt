package com.example.placesapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.placesapi.remote.RetrofitClient
import com.example.placesapi.remote.data.CountryResponseDto
import kotlinx.coroutines.launch

class CountriesViewModel : ViewModel() {
    val countriesResponse = MutableLiveData<CountryResponseDto>()
    private val api = RetrofitClient.getPlacesApi()

    fun getCountries() {
        viewModelScope.launch {
            val result = api.getCountries()
            countriesResponse.postValue(result.body())
        }
    }
}