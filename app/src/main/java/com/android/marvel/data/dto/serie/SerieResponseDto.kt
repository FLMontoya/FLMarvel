package com.android.marvel.data.dto.serie

import com.google.gson.annotations.SerializedName


data class SerieResponseDto(

    @SerializedName("code") val code: String,
    @SerializedName("status") val status: String,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("attributionText") val attributionText: String,
    @SerializedName("attributionHTML") val attributionHTML: String,
    @SerializedName("data") val dataDto: SerieDataDto,
    @SerializedName("etag") val etag: String

)