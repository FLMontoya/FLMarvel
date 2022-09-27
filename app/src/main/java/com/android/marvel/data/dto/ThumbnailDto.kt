package com.android.marvel.data.dto

import com.google.gson.annotations.SerializedName


data class ThumbnailDto(

    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String

) {

    fun getPortrait() = "$path/portrait_medium.$extension"

    fun getPortraitUncanny() = "$path/portrait_uncanny.$extension"

    fun getLandscape() = "$path/landscape_xlarge.$extension"

    fun getSquare() = "$path/standard_medium.$extension"


}