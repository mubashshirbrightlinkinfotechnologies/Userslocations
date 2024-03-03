package com.example.placesapi.remote.data


import com.google.gson.annotations.SerializedName

class CountryResponseDto : ArrayList<CountryResponseDto.CountryResponseDtoItem>(){
    data class CountryResponseDtoItem(
        val id: Int?,
        val name: String?,
        val phoneCode: Int?,
        val shortName: String?
    )
}