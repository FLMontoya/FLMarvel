package com.android.marvel.data.remote

import com.android.marvel.model.Character
import com.android.marvel.model.DetailItem

interface RemoteDataSource {
    suspend fun requestCharacters(page: Int, size: Int, query: String? = ""): List<Character>
    suspend fun requestCharacterComic(characterId: String, page: Int, size: Int): List<DetailItem>
    suspend fun requestCharacterSerie(characterId: String, page: Int, size: Int): List<DetailItem>
    suspend fun requestCharacterEvent(characterId: String, page: Int, size: Int): List<DetailItem>
}