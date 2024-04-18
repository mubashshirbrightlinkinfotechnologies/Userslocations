package com.example.placesapi.remote.data

data class MapResponseDto(
    val activeCount: Int?,
    val inactiveCount: Int?,
    val locations: List<Location>?,
    val totalCount: Int?
)