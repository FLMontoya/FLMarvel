package com.android.marvel.data.dto.character


import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String
)