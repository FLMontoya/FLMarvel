package com.android.marvel.data.remote

import com.android.marvel.data.dto.character.CharacterResponseDto
import com.android.marvel.model.Character
import com.android.marvel.model.Comic
import com.android.marvel.model.Event
import com.android.marvel.model.MarvelItem
import com.android.marvel.model.Serie

interface RemoteDataSource {
    suspend fun requestCharacter(characterId: Int): Character
    suspend fun requestCharacters(page: Int, size: Int, query: String? = ""): List<MarvelItem>
    suspend fun requestCharacterComic(characterId: Int, page: Int, size: Int): List<MarvelItem>
    suspend fun requestCharacterSerie(characterId: Int, page: Int, size: Int): List<MarvelItem>
    suspend fun requestCharacterEvent(characterId: Int, page: Int, size: Int): List<MarvelItem>

    suspend fun requestComic(comicId: Int): Comic
    suspend fun requestComics(page: Int, size: Int): List<MarvelItem>
    suspend fun requestComicCharacter(comicId: Int, page: Int, size: Int): List<MarvelItem>
    suspend fun requestComicEvent(comicId: Int, page: Int, size: Int): List<MarvelItem>

    suspend fun requestEvent(eventId: Int): Event
    suspend fun requestEventCharacter(eventId: Int, page: Int, size: Int): List<MarvelItem>
    suspend fun requestEventComic(eventId: Int, page: Int, size: Int): List<MarvelItem>
    suspend fun requestEventSerie(eventId: Int, page: Int, size: Int): List<MarvelItem>
    suspend fun requestEvents(page: Int, size: Int): List<MarvelItem>

    suspend fun requestSerie(seriesId: Int): Serie
    suspend fun requestSeries(page: Int, size: Int): List<MarvelItem>
    suspend fun requestSerieCharacter(seriesId: Int, page: Int, size: Int): List<MarvelItem>
    suspend fun requestSerieComic(seriesId: Int, page: Int, size: Int): List<MarvelItem>
    suspend fun requestSerieEvent(seriesId: Int, page: Int, size: Int): List<MarvelItem>
}