package com.android.marvel.data.remote

import com.android.marvel.model.Character
import com.android.marvel.model.Comic
import com.android.marvel.model.DetailItem
import com.android.marvel.model.Event

interface RemoteDataSource {
    suspend fun requestCharacter(characterId: Int): Character
    suspend fun requestCharacters(page: Int, size: Int, query: String? = ""): List<Character>
    suspend fun requestCharacterComic(characterId: Int, page: Int, size: Int): List<DetailItem>
    suspend fun requestCharacterSerie(characterId: Int, page: Int, size: Int): List<DetailItem>
    suspend fun requestCharacterEvent(characterId: Int, page: Int, size: Int): List<DetailItem>

    suspend fun requestComic(comicId: Int): Comic
    suspend fun requestComicCharacter(comicId: Int, page: Int, size: Int): List<DetailItem>
    suspend fun requestComicEvent(comicId: Int, page: Int, size: Int): List<DetailItem>

    suspend fun requestEvent(eventId: Int): Event
    suspend fun requestEventCharacter(eventId: Int, page: Int, size: Int): List<DetailItem>
    suspend fun requestEventComic(eventId: Int, page: Int, size: Int): List<DetailItem>


}