package com.android.marvel.data.dto

import com.google.gson.annotations.SerializedName

data class PreviousDto(

    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String
)