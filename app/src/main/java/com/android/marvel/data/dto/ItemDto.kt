package com.android.marvel.data.dto

import com.google.gson.annotations.SerializedName

data class ItemDto(

    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String,
    @SerializedName("type") val type: String? = null

)