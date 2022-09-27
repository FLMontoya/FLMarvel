package com.android.marvel.data.dto.event

import com.google.gson.annotations.SerializedName

data class EventResponseDto(

    @SerializedName("code") val code: String,
    @SerializedName("status") val status: String,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("attributionText") val attributionText: String,
    @SerializedName("attributionHTML") val attributionHTML: String,
    @SerializedName("data") val dataDto: EventDataDto,
    @SerializedName("etag") val etag: String
)