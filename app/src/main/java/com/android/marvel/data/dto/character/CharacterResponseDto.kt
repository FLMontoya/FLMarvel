package com.android.marvel.data.dto.character

import com.google.gson.annotations.SerializedName


data class CharacterResponseDto(
    @SerializedName("data") val dataDto: CharacterDataDto,
    @SerializedName("code") val code: String,
    @SerializedName("status") val status: String,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("attributionText") val attributionText: String,
    @SerializedName("attributionHTML") val attributionHTML: String,
    @SerializedName("etag") val etag: String
)