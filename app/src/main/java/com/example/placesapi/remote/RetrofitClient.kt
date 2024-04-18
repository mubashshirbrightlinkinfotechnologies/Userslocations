package com.example.placesapi.remote

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    var JWT_TOKEN = ""
    private const val BASE_URL = "http://164.52.197.27:8082/"

    private fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun provideSecureRetrofitClient(): Retrofit {
        val interceptor = BearerTokenInterceptor(JWT_TOKEN)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getAuthApi(): AuthApi {
        return provideRetrofitClient()
            .create(AuthApi::class.java)
    }

    fun getPlacesApi(): PlacesApiInterface {
        return provideSecureRetrofitClient()
            .create(PlacesApiInterface::class.java)
    }

}
