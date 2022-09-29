package com.android.marvel.model

data class MarvelItem(
    val id: Int,
    val name: String,
    val image: String,
    val marvelItemType: MarvelItemType) {
}

enum class MarvelItemType(private val title: String) {
    CHARACTER("CHARACTER"), COMIC("COMIC"), SERIE("SERIE"), EVENT("EVENT");

    fun getTitle() = title
}