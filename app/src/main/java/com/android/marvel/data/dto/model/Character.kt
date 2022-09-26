package com.android.marvel.data.dto.model

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val imagePath: String,
    val imageExtension: String
) {

    fun getPortrait() = "$imagePath/portrait_medium.$imageExtension"

    fun getPortraitUncanny() = "$imagePath/portrait_uncanny.$imageExtension"

    fun getLandscape() = "$imagePath/landscape_xlarge.$imageExtension"

    fun getSquare() = "$imagePath/standard_medium.$imageExtension"

}
