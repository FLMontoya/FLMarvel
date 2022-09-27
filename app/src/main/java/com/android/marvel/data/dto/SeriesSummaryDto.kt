package com.android.marvel.data.dto

import com.google.gson.annotations.SerializedName

data class SeriesSummaryDto(

    @SerializedName("available") val available: Int,
    @SerializedName("returned") val returned: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: ArrayList<ItemDto> = arrayListOf()

)