package com.android.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val imagePath: String,
    val imageExtension: String,
    val comicsCount: Int,
    val eventsCount: Int,
    val seriesCount: Int,
    val detailLink: String? = null
) : Parcelable {

    fun getPortrait() = "$imagePath/portrait_medium.$imageExtension"

    fun getPortraitUncanny() = "$imagePath/portrait_uncanny.$imageExtension"

    fun getLandscape() = "$imagePath/landscape_xlarge.$imageExtension"

    fun getSquare() = "$imagePath/standard_medium.$imageExtension"

    fun getDetail() = "$imagePath/detail.$imageExtension"


}
