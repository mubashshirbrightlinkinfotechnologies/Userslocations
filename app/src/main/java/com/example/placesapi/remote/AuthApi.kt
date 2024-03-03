package com.example.placesapi.remote

import com.example.placesapi.remote.data.LoginRequestDto
import com.example.placesapi.remote.data.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/signin")
    suspend fun login(@Body requestDto: LoginRequestDto): Response<LoginResponseDto>
}