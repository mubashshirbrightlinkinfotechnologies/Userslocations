package com.example.placesapi.remote.data


import com.google.gson.annotations.SerializedName

data class LoginRequestDto(
    val password: String?,
    val username: String?
)