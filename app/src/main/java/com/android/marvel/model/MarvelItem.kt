package com.android.marvel.model

data class MarvelItem(
    val id: Int,
    val name: String,
    val image: String,
    val marvelItemType: MarvelItemType) {
}

enum class MarvelItemType(private val title: String) {
    CHARACTER("Personajes"), COMIC("Comics"), SERIE("Series"), EVENT("Eventos");

    fun getTitle() = title
}