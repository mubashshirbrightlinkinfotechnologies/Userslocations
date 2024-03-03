package com.example.placesapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.placesapi.remote.RetrofitClient
import com.example.placesapi.remote.data.CityResponseDto
import com.example.placesapi.remote.data.CountryResponseDto
import com.example.placesapi.remote.data.StateResponseDto
import kotlinx.coroutines.launch

class CityViewModel : ViewModel() {
    val cityResponse = MutableLiveData<CityResponseDto>()
    private val api = RetrofitClient.getPlacesApi()

    fun getCities(id: Int) {
        viewModelScope.launch {
            val result = api.getCities(id)
            cityResponse.postValue(result.body())
        }
    }
}