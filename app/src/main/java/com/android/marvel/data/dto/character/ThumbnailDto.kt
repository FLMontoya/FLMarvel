package com.android.marvel.data.dto.character


import com.google.gson.annotations.SerializedName

data class ThumbnailDto(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)