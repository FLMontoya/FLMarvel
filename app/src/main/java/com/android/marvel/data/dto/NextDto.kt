package com.android.marvel.data.dto

import com.google.gson.annotations.SerializedName

data class NextDto(

    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String
)