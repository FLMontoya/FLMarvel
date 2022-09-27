package com.android.marvel.data.dto

import com.google.gson.annotations.SerializedName


data class MarvelUrlDto(

    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)