package com.example.placesapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.placesapi.remote.RetrofitClient
import com.example.placesapi.remote.data.CountryResponseDto
import com.example.placesapi.remote.data.StateResponseDto
import kotlinx.coroutines.launch

class StatesViewModel : ViewModel() {
    val statesResponse = MutableLiveData<StateResponseDto>()
    private val api = RetrofitClient.getPlacesApi()

    fun getStates(id: Int) {
        viewModelScope.launch {
            val result = api.getStates(id)
            statesResponse.postValue(result.body())
        }
    }
}