package com.example.placesapi.remote.data


import com.google.gson.annotations.SerializedName

class CityResponseDto : ArrayList<CityResponseDto.CityResponseDtoItem>(){
    data class CityResponseDtoItem(
        val id: Int?,
        val name: String?,
        val states: States?
    ) {
        data class States(
            val countries: Countries?,
            val id: Int?,
            val name: String?
        ) {
            data class Countries(
                val id: Int?,
                val name: String?,
                val phoneCode: Int?,
                val shortName: String?
            )
        }
    }
}