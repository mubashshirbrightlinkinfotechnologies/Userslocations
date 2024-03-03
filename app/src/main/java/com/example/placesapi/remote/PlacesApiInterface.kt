package com.example.placesapi.remote

import com.example.placesapi.remote.data.CityResponseDto
import com.example.placesapi.remote.data.CountryResponseDto
import com.example.placesapi.remote.data.StateResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlacesApiInterface {

    @GET("/getcountries")
    suspend fun getCountries(): Response<CountryResponseDto>

    @GET("/getstates/{id}")
    suspend fun getStates(
        @Path("id") id: Int
    ): Response<StateResponseDto>

    @GET("/getcities/{id}")
    suspend fun getCities(
        @Path("id") id: Int
    ): Response<CityResponseDto>

}