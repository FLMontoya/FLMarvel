package com.android.marvel.model

sealed class MarvelModel {
    abstract val id:  Int
    abstract val name: String
    abstract val description: String?
    abstract val imagePath: String
    abstract val imageExtension: String
    abstract val comicsCount: Int
    abstract val eventsCount: Int
    abstract val seriesCount: Int
    abstract val charactersCount: Int
    abstract val detailLink: String?


    fun isComicsEnabled() = comicsCount > 0
    fun isEventsEnabled() = eventsCount > 0
    fun isSeriesEnabled() = seriesCount > 0
    fun isCharactersEnabled() = charactersCount > 0

    fun getDetail() = "$imagePath/detail.$imageExtension"

    fun getFormatDescription() : String {
        return when {
            description.isNullOrBlank() -> {
                "Descripción no disponible"
            }
            else -> description!!
        }

    }

}