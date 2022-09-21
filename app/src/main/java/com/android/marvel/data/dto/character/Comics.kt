package com.android.marvel.data.dto.character


import com.google.gson.annotations.SerializedName

data class Comics(
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<Item>,
    @SerializedName("returned") val returned: Int
)