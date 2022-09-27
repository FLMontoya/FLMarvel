package com.android.marvel.data.dto

import com.google.gson.annotations.SerializedName

data class CharacterSummaryDto(

    @SerializedName("available") val available: String,
    @SerializedName("returned") val returned: String,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: ArrayList<ItemDto> = arrayListOf()

)