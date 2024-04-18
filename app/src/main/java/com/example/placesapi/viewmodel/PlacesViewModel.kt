package com.example.placesapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.placesapi.remote.RetrofitClient
import com.example.placesapi.remote.data.CityResponseDto
import com.example.placesapi.remote.data.CountryResponseDto
import com.example.placesapi.remote.data.MapResponseDto
import com.example.placesapi.remote.data.StateResponseDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlacesViewModel : ViewModel() {
    val countriesResponse = MutableLiveData<CountryResponseDto>()
    val statesResponse = MutableLiveData<StateResponseDto>()
    val cityResponse = MutableLiveData<CityResponseDto>()
    val mapResponse = MutableLiveData<MapResponseDto>()

    private val api = RetrofitClient.getPlacesApi()

    fun getCountries() {
        viewModelScope.launch {
            val result = api.getCountries()
            countriesResponse.postValue(result.body())
        }
    }
    fun getCities(id: Int) {
        viewModelScope.launch {
            val result = api.getCities(id)
            cityResponse.postValue(result.body())
        }
    }
    fun getStates(id: Int) {
        viewModelScope.launch {
            val result = api.getStates(id)
            statesResponse.postValue(result.body())
        }
    }

    fun getMapData() {
        viewModelScope.launch {
            val result = api.getMaps()
            mapResponse.postValue(result.body())
        }
    }

    fun keepUpdatingLocation() {
        viewModelScope.launch {
            while (true){
                getMapData()
                delay(10000L)
            }
        }
    }
}