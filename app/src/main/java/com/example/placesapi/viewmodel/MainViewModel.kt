package com.example.placesapi.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.placesapi.remote.RetrofitClient
import com.example.placesapi.remote.data.LoginRequestDto
import com.example.placesapi.remote.data.LoginResponseDto
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val api = RetrofitClient.getAuthApi()

    val errorResult = MutableLiveData("")
    val loginResponse = MutableLiveData<LoginResponseDto>()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = api.login(
                LoginRequestDto(
                    password = password,
                    username = email
                )
            )
            if (result.isSuccessful.not() || result.code() != 200) {
                errorResult.postValue("Login error")
            } else {
                loginResponse.postValue(result.body())
            }
        }
    }
}
