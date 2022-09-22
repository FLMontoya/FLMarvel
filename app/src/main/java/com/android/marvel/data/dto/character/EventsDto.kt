package com.android.marvel.data.dto.character


import com.google.gson.annotations.SerializedName

data class EventsDto(
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<ItemDto>,
    @SerializedName("returned") val returned: Int
)