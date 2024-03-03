package com.example.placesapi.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class BearerTokenInterceptor(private val token: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")

        val newRequest = requestBuilder.build()
        return chain.proceed(newRequest)
    }
}