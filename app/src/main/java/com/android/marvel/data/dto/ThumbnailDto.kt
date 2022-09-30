package com.android.marvel.data.dto

import com.google.gson.annotations.SerializedName


data class ThumbnailDto(

    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String

) {

    fun getPortrait() = "$path/portrait_fantastic.$extension"

}