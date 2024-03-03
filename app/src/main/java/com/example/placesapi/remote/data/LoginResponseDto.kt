package com.example.placesapi.remote.data


import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    val email: String?,
    val emailAccess: Int?,
    val enabled: Boolean?,
    val expiryDate: Any?,
    val id: Int?,
    val integrationAccess: Int?,
    val jwtToken: String?,
    val name: String?,
    val role: String?,
    val timeZone: Any?,
    val trackingAccess: Int?
)