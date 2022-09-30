package com.android.marvel.data.dto

import com.google.gson.annotations.SerializedName

data class Prices(

    @SerializedName("price") val price: Double,
    @SerializedName("type") val type: String
)