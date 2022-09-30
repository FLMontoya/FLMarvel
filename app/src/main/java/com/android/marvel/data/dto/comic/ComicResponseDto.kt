package com.android.marvel.data.dto.comic

import com.google.gson.annotations.SerializedName

data class ComicResponseDto(

    @SerializedName("copyright") val copyright: String,
    @SerializedName("code") val code: Int,
    @SerializedName("data") val dataDto: ComicDataDto,
    @SerializedName("attributionHTML") val attributionHTML: String,
    @SerializedName("attributionText") val attributionText: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("status") val status: String
)