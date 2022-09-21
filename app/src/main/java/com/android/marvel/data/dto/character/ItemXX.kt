package com.android.marvel.data.dto.character


import com.google.gson.annotations.SerializedName

data class ItemXX(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String
)