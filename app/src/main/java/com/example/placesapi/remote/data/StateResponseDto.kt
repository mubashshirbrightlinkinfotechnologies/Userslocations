package com.example.placesapi.remote.data


import com.google.gson.annotations.SerializedName

class StateResponseDto : ArrayList<StateResponseDto.StateResponseDtoItem>(){
    data class StateResponseDtoItem(
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